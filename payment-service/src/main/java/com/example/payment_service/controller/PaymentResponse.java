package com.example.payment_service.controller;

import java.time.LocalDateTime;

public class PaymentResponse {
    private Long paymentId;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;
    private String orderId;
    private String username;
    private Long policyId;

    public PaymentResponse() {
		super();
	}

	public PaymentResponse(Long paymentId, Double amount, String status, LocalDateTime createdAt, String orderId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.orderId = orderId;
    }
	

	public PaymentResponse(Long paymentId, Double amount, String status, LocalDateTime createdAt, String orderId,
			String username, Long policyId) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.createdAt = createdAt;
		this.orderId = orderId;
		this.username = username;
		this.policyId = policyId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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
	

    // getters and setters
    
}
