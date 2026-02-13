package com.insurance.dto;

public class ClaimResponse {
	String status;
	String note;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ClaimResponse(String status, String note) {
		super();
		this.status = status;
		this.note = note;
	}
	@Override
	public String toString() {
		return "ClaimResponse [status=" + status + ", note=" + note + "]";
	}
	
}
