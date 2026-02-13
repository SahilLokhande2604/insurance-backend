package com.insurance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.Claim;
import com.insurance.service.ClaimServiceImplementation;
import com.insurance.dto.*;




@RestController
@RequestMapping("/claim")
public class ClaimController {

	@Autowired
	ClaimServiceImplementation claimService;
	
	
//	File a Claim
	@PostMapping("/addClaim")
	public Claim addClaim(@RequestBody Claim request) {
		return claimService.addClaim(request);
	}
	
	
//	Claim Status Tracking
	@GetMapping("/claimStatus/{id}")
	public String checkClaimStatus(@PathVariable("id") Long claimId) {
		return claimService.checkClaimStatus(claimId);
	}
	
	//returns all claims 
	@GetMapping("/claimReview/all")
	public List<Claim> getAllClaimReview(){
		return claimService.getAllClaimReview();
	}
	
	
//	Claim Review (Admin/Claim Manager)
	@PostMapping("/claimReview")
	public ClaimResponse claimReview(@RequestBody ClaimRequestDto request){
		return claimService.claimReview(request);
		
	}
	


}
