//package com.customersupport.service;
//
////src/main/java/com/insurance/support/service/SupportService.java
//
//import com.customersupport.client.PolicyClient;
//import com.customersupport.dto.PolicyChangeRequest;
//import com.customersupport.dto.PolicyDto;
//import com.customersupport.exception.NotFoundException;
//import com.customersupport.model.Ticket;
//import com.customersupport.model.TicketType;
//import com.customersupport.repo.TicketRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.OffsetDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class SupportService {
//
//    private final PolicyClient policyClient;
//    private final TicketRepository ticketRepo;
//    
//
//    public SupportService(PolicyClient policyClient, TicketRepository ticketRepo) {
//		super();
//		this.policyClient = policyClient;
//		this.ticketRepo = ticketRepo;
//	}
//
//	// Browse all policies (catalog)
//    public List<PolicyDto> listAllPolicies() {
//        return policyClient.getAllPolicies();
//    }
//
//    // View user's policies
////    public List<PolicyDto> listPoliciesByUser(Long userId) {
////        return policyClient.getPoliciesByUserId(userId);
////    }
//    public List<PolicyDto> listPoliciesByUser(Long userId) {
//        return policyClient.getPoliciesByUserId(userId);
//    }
//    
//    public List<PolicyDto> listPoliciesByUsername(String username) {
//        return policyClient.getPoliciesByUsername(username);
//    }
//
//
//    public PolicyDto getPolicy(Long policyId) {
//        return policyClient.getPolicyById(policyId);
//    }
//
//    public Ticket raiseIssue(String raisedBy, Long policyId, String subject, String description) {
//        Ticket t = new Ticket();
//        t.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
//        t.setPolicyId(policyId);
//        t.setType(TicketType.ISSUE);
//        t.setSubject(subject);
//        t.setDescription(description);
//        t.setCreatedAt(OffsetDateTime.now());
//        t.setUpdatedAt(OffsetDateTime.now());
//
//        Ticket saved = ticketRepo.save(t);
//
//        return saved;
//    }
//
//    public Ticket requestPolicyChange(String raisedBy, PolicyChangeRequest req) {
//        PolicyDto policy = policyClient.getPolicyById(req.getPolicyId());
//        if (policy == null)
//            throw new NotFoundException("Policy not found: " + req.getPolicyId());
//
//        Ticket t = new Ticket();
//        t.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
//        t.setPolicyId(req.getPolicyId());
//        t.setType(TicketType.POLICY_CHANGE);
//        t.setSubject("Policy change: " + req.getChangeType());
//        t.setDescription(req.getDetails());
//        t.setCreatedAt(OffsetDateTime.now());
//        t.setUpdatedAt(OffsetDateTime.now());
//
//        Ticket saved = ticketRepo.save(t);
//
//        return saved;
//    }
//
//    // // Raise an issue ticket
//    // public Ticket raiseIssue(String raisedBy, Long policyId, String subject,
//    // String description) {
//    // Ticket t = new Ticket();
//    // t.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
//    // t.setPolicyId(policyId);
//    // t.setType(TicketType.ISSUE);
//    // t.setSubject(subject);
//    // t.setDescription(description);
//    // t.setCreatedAt(OffsetDateTime.now());
//    // t.setUpdatedAt(OffsetDateTime.now());
//    // return ticketRepo.save(t);
//    // }
//    //
//    //
//    //// Publish event
//    // eventProducer.publishTicketCreated(new TicketCreatedEvent(
//    // saved.getId(),
//    // saved.getType().name(),
//    // saved.getSubject(),
//    // saved.getDescription(),
//    // saved.getPolicyId(),
//    // saved.getRaisedBy(),
//    // saved.getCreatedAt()
//    // ));
//    //
//    // return saved;
//    // }
//    //
//    //
//    //
//    // // Request a policy change (stored as ticket)
//    // public Ticket requestPolicyChange(String raisedBy, PolicyChangeRequest req) {
//    // // Validate policy exists
//    // PolicyDto policy = policyClient.getPolicyById(req.getPolicyId());
//    // if (policy == null) {
//    // throw new NotFoundException("Policy not found: " + req.getPolicyId());
//    // }
//    // Ticket t = new Ticket();
//    // t.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
//    // t.setPolicyId(req.getPolicyId());
//    // t.setType(TicketType.POLICY_CHANGE);
//    // t.setSubject("Policy change: " + req.getChangeType());
//    // t.setDescription(req.getDetails());
//    // t.setCreatedAt(OffsetDateTime.now());
//    // t.setUpdatedAt(OffsetDateTime.now());
//    // return ticketRepo.save(t);
//    // }
//
//    public Ticket getTicket(Long id) {
//        return ticketRepo.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found: " + id));
//    }
//
//    public List<Ticket> ticketsByUser(String raisedBy) {
//        return ticketRepo.findByRaisedBy(raisedBy);
//    }
//
//    public List<Ticket> ticketsByPolicy(Long policyId) {
//        return ticketRepo.findByPolicyId(policyId);
//    }
//    
//    public List<Ticket> allTickets() {
//        return ticketRepo.findAll();
//    }
//
//}

package com.customersupport.service;

import com.customersupport.client.PolicyClient;
import com.customersupport.dto.PolicyChangeRequest;
import com.customersupport.dto.PolicyDto;
import com.customersupport.exception.NotFoundException;
import com.customersupport.kafka.SupportEventProducer;
import com.customersupport.model.Ticket;
import com.customersupport.model.TicketStatus;
import com.customersupport.model.TicketType;
import com.customersupport.repo.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final PolicyClient policyClient;
    private final TicketRepository ticketRepo;
    private final SupportEventProducer supportEventProducer;

    
    

//    public SupportService(PolicyClient policyClient, TicketRepository ticketRepo) {
//		super();
//		this.policyClient = policyClient;
//		this.ticketRepo = ticketRepo;
//	}
    
    public SupportService(PolicyClient policyClient, TicketRepository ticketRepo,
		SupportEventProducer supportEventProducer) {
	super();
	this.policyClient = policyClient;
	this.ticketRepo = ticketRepo;
	this.supportEventProducer = supportEventProducer;
}

	public List<PolicyDto> listAllPolicies() {
        return policyClient.getAllPolicies();
    }



	public List<PolicyDto> listPoliciesByUsername(String username) {
        return policyClient.getPoliciesByUsername(username);
    }

    public PolicyDto getPolicy(Long policyId) {
        return policyClient.getPolicyById(policyId);
    }

//    public Ticket raiseIssue(String raisedBy,
//                             Long policyId,
//                             String subject,
//                             String description) {
//
//        Ticket ticket = new Ticket();
//        ticket.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
//        ticket.setPolicyId(policyId);
//        ticket.setType(TicketType.ISSUE);
//        ticket.setSubject(subject);
//        ticket.setDescription(description);
//        ticket.setCreatedAt(OffsetDateTime.now());
//        ticket.setUpdatedAt(OffsetDateTime.now());
//
//        Ticket saved = ticketRepo.save(ticket);
//
//        supportEventProducer.sendIssueRaisedEvent(
//                saved.getRaisedBy(),
//                saved.getPolicyId()
//        );
//
//        return saved;
//
//    }
    
    public Ticket raiseIssue(String raisedBy,
            Long policyId,
            String subject,
            String description) {

Ticket ticket = new Ticket();
ticket.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
ticket.setPolicyId(policyId);
ticket.setType(TicketType.ISSUE);
ticket.setSubject(subject);
ticket.setDescription(description);
ticket.setCreatedAt(OffsetDateTime.now());
ticket.setUpdatedAt(OffsetDateTime.now());

Ticket saved = ticketRepo.save(ticket);

//// 🔥 SEND KAFKA EVENT
//supportEventProducer.sendTicketRaisedEvent(
//        saved.getRaisedBy(),
//        saved.getId()
//);

return saved;
}


    public Ticket requestPolicyChange(String raisedBy,
                                      PolicyChangeRequest req) {

        PolicyDto policy = policyClient.getPolicyById(req.getPolicyId());

        if (policy == null)
            throw new NotFoundException("Policy not found");

        Ticket ticket = new Ticket();
        ticket.setRaisedBy(raisedBy == null ? "anonymous" : raisedBy);
        ticket.setPolicyId(req.getPolicyId());
        ticket.setType(TicketType.POLICY_CHANGE);
        ticket.setSubject("Policy change: " + req.getChangeType());
        ticket.setDescription(req.getDetails());
        ticket.setCreatedAt(OffsetDateTime.now());
        ticket.setUpdatedAt(OffsetDateTime.now());

        Ticket saved = ticketRepo.saveAndFlush(ticket);

//        supportEventProducer.sendTicketRaisedEvent(
//                saved.getRaisedBy(),
//                saved.getId()
//        );

        return saved;


    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    public List<Ticket> ticketsByUser(String username) {
        return ticketRepo.findByRaisedBy(username);
    }

    public List<Ticket> ticketsByPolicy(Long policyId) {
        return ticketRepo.findByPolicyId(policyId);
    }

    public List<Ticket> allTickets() {
        return ticketRepo.findAll();
    }
    
    public Ticket takeAdminAction(Long ticketId,
            String adminUsername,
            TicketStatus status,
            String remarks) {

Ticket ticket = ticketRepo.findById(ticketId)
.orElseThrow(() -> new RuntimeException("Ticket not found"));

ticket.setStatus(status);
ticket.setAdminRemarks(remarks);

if (status == TicketStatus.RESOLVED || status == TicketStatus.REJECTED) {
ticket.setResolvedBy(adminUsername);
ticket.setResolvedAt(LocalDateTime.now());


}

Ticket saved = ticketRepo.save(ticket);

if (status == TicketStatus.RESOLVED) {
//	supportEventProducer.sendTicketResolvedEvent(
//            saved.getRaisedBy(),
//            saved.getId()
//    );
}
if (status == TicketStatus.RESOLVED) {
//    supportEventProducer.sendTicketResolvedEvent(
//            saved.getRaisedBy(),
//            saved.getId()
//    );
}


return saved;

}

}
