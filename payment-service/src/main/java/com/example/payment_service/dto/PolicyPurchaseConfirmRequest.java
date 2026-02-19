package com.example.payment_service.dto;

public class PolicyPurchaseConfirmRequest {

    private String username;
    private Long policyId;
    private Long paymentId;

    public PolicyPurchaseConfirmRequest() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public PolicyPurchaseConfirmRequest(String username, Long policyId, Long paymentId) {
		super();
		this.username = username;
		this.policyId = policyId;
		this.paymentId = paymentId;
	}
    
    
}
