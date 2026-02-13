package com.insurance.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
 
public class Policy {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;
	private Long policyNumber;
    private String policyName;
    private String description;
    private Double basePrice;
    private String policyCategory;
    private Long PolicyPremium;
    private Integer durationMonths;
    private Double coverageAmount;
    @Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyName=" + policyName
				+ ", description=" + description + ", basePrice=" + basePrice + ", policyCategory=" + policyCategory
				+ ", PolicyPremium=" + PolicyPremium + ", durationMonths=" + durationMonths + ", coverageAmount="
				+ coverageAmount + ", exclusions=" + exclusions + ", terms=" + terms + ", policyStartDate="
				+ policyStartDate + ", policyEndDate=" + policyEndDate + "]";
	}
	private String exclusions;
    private String terms;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    public Policy(Long policyId, Long policyNumber, String policyName, String description, Double basePrice,
			String policyCategory, Long policyPremium, Integer durationMonths, Double coverageAmount, String exclusions,
			String terms, LocalDate policyStartDate, LocalDate policyEndDate) {
		super();
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.policyName = policyName;
		this.description = description;
		this.basePrice = basePrice;
		this.policyCategory = policyCategory;
		PolicyPremium = policyPremium;
		this.durationMonths = durationMonths;
		this.coverageAmount = coverageAmount;
		this.exclusions = exclusions;
		this.terms = terms;
		this.policyStartDate = policyStartDate;
		this.policyEndDate = policyEndDate;
	}
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public Long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	public String getPolicyCategory() {
		return policyCategory;
	}
	public void setPolicyCategory(String policyCategory) {
		this.policyCategory = policyCategory;
	}
	public Long getPolicyPremium() {
		return PolicyPremium;
	}
	public void setPolicyPremium(Long policyPremium) {
		PolicyPremium = policyPremium;
	}
	public Integer getDurationMonths() {
		return durationMonths;
	}
	public void setDurationMonths(Integer durationMonths) {
		this.durationMonths = durationMonths;
	}
	public Double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public String getExclusions() {
		return exclusions;
	}
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
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
	
}