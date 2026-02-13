package com.insurance.dto;

import com.insurance.model.Claim;

public class ClaimRequestDto {
	User user;
	Policy policy;
	Claim claim;
	
	public Claim getClaim() {
		return claim;
	}
	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public ClaimRequestDto(User user, Policy policy, Claim claim) {
		super();
		this.user = user;
		this.policy = policy;
		this.claim = claim;
	}
	
	
	
}
