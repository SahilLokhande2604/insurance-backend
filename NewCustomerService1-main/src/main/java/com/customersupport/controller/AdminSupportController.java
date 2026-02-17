package com.customersupport.controller;

import com.customersupport.dto.TicketActionRequest;
import com.customersupport.model.Ticket;
import com.customersupport.service.SupportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/support")
@RequiredArgsConstructor
public class AdminSupportController {

    private final SupportService service;
    
    

    public AdminSupportController(SupportService service) {
		super();
		this.service = service;
	}

	// ✅ Get all tickets
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(service.allTickets());
    }

    // ✅ Update / Resolve / Reject Ticket
    @PutMapping("/tickets/{id}/action")
    public ResponseEntity<Ticket> takeAction(
            @PathVariable Long id,
            @RequestHeader("X-USERNAME") String adminUsername,
            @Valid @RequestBody TicketActionRequest request) {

        Ticket updated = service.takeAdminAction(
                id,
                adminUsername,
                request.getStatus(),
                request.getAdminRemarks()
        );

        return ResponseEntity.ok(updated);
    }
}
