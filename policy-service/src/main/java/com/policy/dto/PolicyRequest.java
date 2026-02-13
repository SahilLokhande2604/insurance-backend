package com.policy.dto;

public class PolicyRequest {

    private String policyType;
    private Double coverageAmount;
    private Double premiumAmount;
    private Long userId;
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public Double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public Double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public PolicyRequest() {
		super();
	}
	public PolicyRequest(String policyType, Double coverageAmount, Double premiumAmount, Long userId) {
		super();
		this.policyType = policyType;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
		this.userId = userId;
	}
    
    
}
