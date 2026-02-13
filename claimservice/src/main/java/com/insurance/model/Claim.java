package com.insurance.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long claimId;
	private Long policyId;
	private Long customerId;
	private String claimType;
	private String status="Pending";
	private Double amount;
	private String note;
	private String documentType;
	private String documentId;
	private Double coverageAmount;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;

	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public Double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", policyId=" + policyId + ", customerId=" + customerId + ", claimType="
				+ claimType + ", status=" + status + ", amount=" + amount + ", note=" + note + ", documentType="
				+ documentType + ", documentId=" + documentId + ", coverageAmount=" + coverageAmount
				+ ", policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate + "]";
	}
	
	
	
	
	
	
	
	

}
