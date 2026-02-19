package com.example.payment_service.entity;


import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
 
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
 
    private String orderId;
    private Long userId;
    private Long policyId;
    private Double amount;
    private String paymentMethod;
    private String username;
 
    private String transactionId;
    private String razorpayOrderId;
    
    public Payment(Long paymentId, String orderId, Long userId, Double amount, String paymentMethod, String username,
			String transactionId, String razorpayOrderId, String razorpayPaymentId, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.username = username;
		this.transactionId = transactionId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Payment(Long paymentId, String orderId, Long userId, Long policyId, Double amount, String paymentMethod,
			String username, String transactionId, String razorpayOrderId, String razorpayPaymentId, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.userId = userId;
		this.policyId = policyId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.username = username;
		this.transactionId = transactionId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String razorpayPaymentId;
 
    private String status;
 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
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
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Payment(Long paymentId, String orderId, Long userId, Double amount, String paymentMethod, String transactionId,
			String razorpayOrderId, String razorpayPaymentId, String status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionId = transactionId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Payment() {
		super();
	}

    
    
}
 