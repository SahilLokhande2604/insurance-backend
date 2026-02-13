package com.example.payment_service.dto;


import lombok.Data;
 
@Data
public class CreatePaymentRequest {
 
    private Long orderId;
    private Long userId;
    private Double amount;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public CreatePaymentRequest() {
		super();
	}
	public CreatePaymentRequest(Long orderId, Long userId, Double amount) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}
}
 