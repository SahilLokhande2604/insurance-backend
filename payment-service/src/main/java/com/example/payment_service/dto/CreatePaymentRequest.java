package com.example.payment_service.dto;

public class CreatePaymentRequest {

    private String username;   // 🔥 PRIMARY KEY
    private Long policyId;     // 🔥 policy user is buying
    private Double amount;

    public CreatePaymentRequest() {}

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CreatePaymentRequest(String username, Long policyId, Double amount) {
		super();
		this.username = username;
		this.policyId = policyId;
		this.amount = amount;
	}
    
}
