package com.example.payment_service.dto;


import lombok.Data;
 
@Data
public class VerifyPaymentRequest {
 
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String signature;
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public VerifyPaymentRequest(String razorpayOrderId, String razorpayPaymentId, String signature) {
		super();
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.signature = signature;
	}
	public VerifyPaymentRequest() {
		super();
	}
    
    
}
 