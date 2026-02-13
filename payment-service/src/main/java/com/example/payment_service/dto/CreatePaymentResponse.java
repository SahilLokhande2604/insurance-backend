package com.example.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class CreatePaymentResponse {
 
    private String razorpayOrderId;
    private Double amount;
    private String currency;
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public CreatePaymentResponse(String razorpayOrderId, Double amount, String currency) {
		super();
		this.razorpayOrderId = razorpayOrderId;
		this.amount = amount;
		this.currency = currency;
	}
	public CreatePaymentResponse() {
		super();
	}
    
}
 