package com.customersupport.dto;

import com.customersupport.model.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketActionRequest {

    @NotNull
    private TicketStatus status;

    private String adminRemarks;

	public TicketActionRequest() {
		super();
	}

	public TicketActionRequest(@NotNull TicketStatus status, String adminRemarks) {
		super();
		this.status = status;
		this.adminRemarks = adminRemarks;
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
    
    
}


