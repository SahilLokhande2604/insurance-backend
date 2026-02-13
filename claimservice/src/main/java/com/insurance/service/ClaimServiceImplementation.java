package com.insurance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.insurance.dto.ClaimRequestDto;
import com.insurance.dto.ClaimResponse;
import com.insurance.dto.Policy;
import com.insurance.dto.User;
import com.insurance.enums.DownstreamService;
import com.insurance.exception.DownstreamUnavailableException;
import com.insurance.model.Claim;
import com.insurance.repository.ClaimRepository;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class ClaimServiceImplementation implements ClaimService{

	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Claim addClaim(Claim request) {
		// TODO Auto-generated method stub
		return claimRepository.save(request);
	}
	
	@Override
	public  String checkClaimStatus(Long claimId) {
		Optional<Claim> response = claimRepository.findById(claimId);
		Claim claim = response.get();
		return claim.getStatus();
	}
	
	@Override
	public  Optional<Claim> getClaim(Long claimId) {
		return claimRepository.findById(claimId);
	}

	@Override
	public List<Claim> getAllClaimReview() {
		return claimRepository.findAll();
	}
	
	
	

	@Override
	@CircuitBreaker(name="claimservice",fallbackMethod = "fallbackmethodforpostRequest")
	public ClaimResponse claimReview(ClaimRequestDto request) {
		if (request == null) return new ClaimResponse("FAIL", "NULL Request");
			Long userId = request.getUser().getId(); 
			Long policyId = request.getPolicy().getPolicyId();

		User user;
		Policy policy;
		
	    try {
	        user = restTemplate.getForObject("http://localhost:8080/api/{id}", User.class, userId);
	    } catch (HttpStatusCodeException ex) {
	        throw new DownstreamUnavailableException(DownstreamService.USER,
	            "User service HTTP error: " + ex.getStatusCode().value(), ex, ex.getStatusCode().value());
	    } catch (ResourceAccessException ex) { // connection refused / timeout
	        throw new DownstreamUnavailableException(DownstreamService.USER,
	            "User service unreachable: " + ex.getMessage(), ex, -1);
	    } catch (RestClientException ex) {
	        throw new DownstreamUnavailableException(DownstreamService.USER,
	            "User service error: " + ex.getMessage(), ex, -1);
	    }
	    

		try {
		        policy = restTemplate.getForObject("http://localhost:8181/api/policies/user/{userId}", Policy.class, userId);
		    } catch (HttpStatusCodeException ex) {
		        throw new DownstreamUnavailableException(DownstreamService.POLICY,
		            "Policy service HTTP error: " + ex.getStatusCode().value(), ex, ex.getStatusCode().value());
		    } catch (ResourceAccessException ex) {
		        throw new DownstreamUnavailableException(DownstreamService.POLICY,
		            "Policy service unreachable: " + ex.getMessage(), ex, -1);
		    } catch (RestClientException ex) {
		        throw new DownstreamUnavailableException(DownstreamService.POLICY,
		            "Policy service error: " + ex.getMessage(), ex, -1);
		    }

			Claim claim = request.getClaim();			

			List<String> errors = new ArrayList<>();

			if (!validateDuration(policy, claim)) {
			errors.add("Duration validation failed: policyStartDate=" + policy.getPolicyStartDate()
			+ ", claimStartDate=" + claim.getPolicyStartDate());
			}
			if (!validateCovarageAmount(policy, claim)) {
			errors.add("Coverage amount validation failed: policyCoverage=" + policy.getCoverageAmount()
			+ ", claimAmount=" + claim.getAmount());
			}
//			if (!validateDocumentDetails(policy, claim, user)) {
//			errors.add("Document details validation failed: claimType/documentId mismatch with user records");
//			}
			if (!validatePolicyCategory(policy, claim)) {
			errors.add("Policy category validation failed: policyCategory=" + policy.getPolicyCategory()
			+ ", claimType=" + claim.getClaimType());
			}

			if (!errors.isEmpty()) {
			String errorMsg = "";
			for(String error: errors) {
				errorMsg+=error+", ";
			}
			
			String existingNote = claim.getNote() == null ? "" : claim.getNote();
			claim.setNote((existingNote.isEmpty() ? "" : existingNote + " | ") + "Validation failed: " + errorMsg);
			claim.setNote(errorMsg);
			claim.setStatus("REJECTED");
			}
			else {
			claim.setStatus("APPROVED");
			claim.setNote("Approved Successfully");
			}
			
			Claim saved = claimRepository.save(claim);
			
			return new ClaimResponse(saved.getStatus(), saved.getNote());
	}
	
	
	public boolean validateDuration(Policy policy, Claim claim) {
		int flag1= policy.getPolicyStartDate().compareTo(claim.getPolicyStartDate());
		int flag2=policy.getPolicyEndDate().compareTo(claim.getPolicyEndDate());
		return flag1 == flag2;
	}
	
	public boolean validateCovarageAmount(Policy policy, Claim claim) {
		return policy.getCoverageAmount()>=claim.getAmount();
	}
	
	public boolean validateDocumentDetails(Policy policy, Claim claim, User user) {
		if(!claim.getDocumentType().equalsIgnoreCase(user.getDocumentType())){
			return false;
		}
		return claim.getDocumentId().equalsIgnoreCase(user.getDocumentId());
	}
	
	
	public boolean validatePolicyCategory(Policy policy, Claim claim) {
		return policy.getPolicyCategory().equalsIgnoreCase(claim.getClaimType());
	}
	


public ClaimResponse fallbackmethodforpostRequest(ClaimRequestDto request, Throwable throwable) {
    String status = "FAIL";
    String msg="";

    if (throwable instanceof DownstreamUnavailableException due) {
        if (due.getService() == DownstreamService.USER) {
            msg+= "User Service is unavailable. ";
        } if (due.getService() == DownstreamService.POLICY) {
            msg+= "Policy Service is unavailable. ";
        } 
    } else if (throwable instanceof CallNotPermittedException) {
        // Circuit is OPEN and short-circuiting
        msg+= "Claim Service circuit is open. Please try again after some time. ";
    } else {
        // Any other unexpected error
        msg+= "Claim processing failed. Please try again after some time. ";
    }

    return new ClaimResponse(status, msg);
}


	
	

	
}
