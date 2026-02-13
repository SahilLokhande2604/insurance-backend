package com.insurance.dto;

public class ClaimResponseDto {
	User user;
	Policy policy;
	public ClaimResponseDto(User user, Policy policy) {
		super();
		this.user = user;
		this.policy = policy;
	}
	
}
