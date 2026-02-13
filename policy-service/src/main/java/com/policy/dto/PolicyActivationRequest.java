package com.policy.dto;


public class PolicyActivationRequest {

    private Long userId;
    private Long paymentId;
    private Double amount;
    private String paymentStatus;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public PolicyActivationRequest() {
		super();
	}
	public PolicyActivationRequest(Long userId, Long paymentId, Double amount, String paymentStatus) {
		super();
		this.userId = userId;
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
	}
	
    
    
}
