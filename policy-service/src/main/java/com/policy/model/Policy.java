package com.policy.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "policies")


public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private String policyType;

    @Column(nullable = false)
    private Double coverageAmount;

    @Column(nullable = false)
    private Double premiumAmount;

    @Column(nullable = false)
    private String status; // PENDING_PAYMENT / ACTIVE / CANCELLED

    @Column(nullable = false)
    private Long userId;

    private Long paymentId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public Policy() {
		super();
	}
	
	public Policy(Long id, String policyNumber, String policyType, Double coverageAmount, Double premiumAmount,
			String status, Long userId, Long paymentId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
		this.status = status;
		this.userId = userId;
		this.paymentId = paymentId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
    
    
}
