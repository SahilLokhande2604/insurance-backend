package com.insurance.dto;

public class User {
	private Long id;
    private String email;
    private String mobileNumber;
    private String documentType;
    private String documentId;

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public User(Long id, String email, String mobileNumber, String documentType, String documentId) {
		super();
		this.id = id;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.documentType = documentType;
		this.documentId = documentId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", mobileNumber=" + mobileNumber + ", documentType="
				+ documentType + ", documentId=" + documentId + "]";
	}
	
	
	
	
	
}
