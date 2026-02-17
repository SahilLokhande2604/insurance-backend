package com.customersupport.model;

//src/main/java/com/insurance/support/model/Ticket.java

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "support_tickets")
@Data
public class Ticket {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 /** Who raised the ticket (username/email/userId string) */
 private String raisedBy;

 /** Link to policy if applicable */
 private Long policyId;

 @Enumerated(EnumType.STRING)
 private TicketType type; // ISSUE or POLICY_CHANGE

 @NotBlank
 private String subject;

 @Column(length = 4000)
 private String description;

 private OffsetDateTime createdAt;
 private OffsetDateTime updatedAt;
 
 @Enumerated(EnumType.STRING)
 private TicketStatus status = TicketStatus.OPEN;

 private String adminRemarks;

 private String resolvedBy;

 private LocalDateTime resolvedAt;

 
 
 public Ticket() {
	super();
 }

 public Ticket(Long id, String raisedBy, Long policyId, TicketType type, @NotBlank String subject, String description,
		OffsetDateTime createdAt, OffsetDateTime updatedAt, TicketStatus status, String adminRemarks, String resolvedBy,
		LocalDateTime resolvedAt) {
	super();
	this.id = id;
	this.raisedBy = raisedBy;
	this.policyId = policyId;
	this.type = type;
	this.subject = subject;
	this.description = description;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.status = status;
	this.adminRemarks = adminRemarks;
	this.resolvedBy = resolvedBy;
	this.resolvedAt = resolvedAt;
}
 
 

 public Long getId() {
	return id;
 }
 public void setId(Long id) {
	this.id = id;
 }
 public String getRaisedBy() {
	return raisedBy;
 }
 public void setRaisedBy(String raisedBy) {
	this.raisedBy = raisedBy;
 }
 public Long getPolicyId() {
	return policyId;
 }
 public void setPolicyId(Long policyId) {
	this.policyId = policyId;
 }
 public TicketType getType() {
	return type;
 }
 public void setType(TicketType type) {
	this.type = type;
 }
 public String getSubject() {
	return subject;
 }
 public void setSubject(String subject) {
	this.subject = subject;
 }
 public String getDescription() {
	return description;
 }
 public void setDescription(String description) {
	this.description = description;
 }
 public OffsetDateTime getCreatedAt() {
	return createdAt;
 }
 public void setCreatedAt(OffsetDateTime createdAt) {
	this.createdAt = createdAt;
 }
 public OffsetDateTime getUpdatedAt() {
	return updatedAt;
 }
 public void setUpdatedAt(OffsetDateTime updatedAt) {
	this.updatedAt = updatedAt;
 }

 public TicketStatus getStatus() {
	return status;
 }

 public void setStatus(TicketStatus status) {
	this.status = status;
 }

 public String getAdminRemarks() {
	return adminRemarks;
 }

 public void setAdminRemarks(String adminRemarks) {
	this.adminRemarks = adminRemarks;
 }

 public String getResolvedBy() {
	return resolvedBy;
 }

 public void setResolvedBy(String resolvedBy) {
	this.resolvedBy = resolvedBy;
 }

 public LocalDateTime getResolvedAt() {
	return resolvedAt;
 }

 public void setResolvedAt(LocalDateTime resolvedAt) {
	this.resolvedAt = resolvedAt;
 }


 
 
 
 
}
