package com.customersupport.dto;

//src/main/java/com/insurance/support/dto/PolicyDto.java

import lombok.Data;

@Data
public class PolicyDto {
 private Long id;
 private String policyNumber;
 private String policyType;
 private Double coverageAmount;
 private Double premiumAmount;
 private String status;
 private Long userId;
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
 public PolicyDto() {
	super();
 }
 public PolicyDto(Long id, String policyNumber, String policyType, Double coverageAmount, Double premiumAmount,
		String status, Long userId) {
	super();
	this.id = id;
	this.policyNumber = policyNumber;
	this.policyType = policyType;
	this.coverageAmount = coverageAmount;
	this.premiumAmount = premiumAmount;
	this.status = status;
	this.userId = userId;
 }
 
 
 
}