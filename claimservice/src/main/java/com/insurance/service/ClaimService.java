package com.insurance.service;

import java.util.List;
import java.util.Optional;

import com.insurance.dto.ClaimRequestDto;
import com.insurance.dto.ClaimResponse;
import com.insurance.dto.ClaimResponseDto;
import com.insurance.model.Claim;

public interface ClaimService {
	
	public Claim addClaim(Claim request);

	public String checkClaimStatus(Long claimId);

	public List<Claim> getAllClaimReview();

	public Optional<Claim> getClaim(Long claimId);
	
	public ClaimResponse claimReview(ClaimRequestDto request);

	
}
