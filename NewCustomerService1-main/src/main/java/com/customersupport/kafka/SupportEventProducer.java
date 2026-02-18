//package com.customersupport.kafka;
//
//import com.customersupport.kafka.dto.SupportEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class SupportEventProducer {
//
//    private static final String TOPIC = "insurance";
//    private static final Logger logger =
//            LoggerFactory.getLogger(SupportEventProducer.class);
//
//    private final KafkaTemplate<String, SupportEvent> kafkaTemplate;
//
//    public SupportEventProducer(
//            KafkaTemplate<String, SupportEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendIssueRaisedEvent(String username, Long policyId) {
//
//        SupportEvent event = buildEvent(
//                username,
//                "ISSUE_RAISED",
//                "Support ticket raised for policy ID: " + policyId,
//                "OPEN"
//        );
//
//        kafkaTemplate.send(TOPIC, event);
//        logger.info("Support issue event sent for {}", username);
//    }
//
//    public void sendPolicyChangeRequestEvent(String username, Long policyId) {
//
//        SupportEvent event = buildEvent(
//                username,
//                "POLICY_CHANGE_REQUEST",
//                "Policy change requested for policy ID: " + policyId,
//                "PENDING"
//        );
//
//        kafkaTemplate.send(TOPIC, event);
//        logger.info("Policy change event sent for {}", username);
//    }
//
//    public void sendTicketResolvedEvent(String username, Long ticketId) {
//
//        SupportEvent event = buildEvent(
//                username,
//                "TICKET_RESOLVED",
//                "Ticket ID " + ticketId + " resolved",
//                "RESOLVED"
//        );
//
//        kafkaTemplate.send(TOPIC, event);
//        logger.info("Ticket resolved event sent for {}", username);
//    }
//
//    private SupportEvent buildEvent(String username,
//                                    String eventType,
//                                    String message,
//                                    String status) {
//
//        return new SupportEvent(
//                username,
//                "support",
//                eventType,
//                message,
//                "customersupport-service",
//                LocalDateTime.now(),
//                status
//        );
//    }
//}


package com.customersupport.kafka;

import com.customersupport.kafka.dto.SupportEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SupportEventProducer {

    private static final String TOPIC = "insurance";
    private static final Logger logger =
            LoggerFactory.getLogger(SupportEventProducer.class);

    private final KafkaTemplate<String, SupportEvent> kafkaTemplate;

    public SupportEventProducer(KafkaTemplate<String, SupportEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void sendTicketRaisedEvent(String username, Long ticketId) {
//
//        SupportEvent event = buildEvent(
//                username,
//                "TICKET_RAISED",
//                "New support ticket raised (ID: " + ticketId + ")",
//                "OPEN"
//        );
//
//        kafkaTemplate.send(TOPIC, event);
//        logger.info("Support event sent for username: {}", username);
//    }
    
    public void sendTicketRaisedEvent(String username, Long ticketId) {

        SupportEvent event = buildEvent(
                username,
                "TICKET_RAISED",
                "New support ticket raised",
                "OPEN"
        );

        kafkaTemplate.send(TOPIC, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        logger.error("❌ Failed to send event", ex);
                    } else {
                        logger.info("✅ Event sent successfully to partition {}",
                                result.getRecordMetadata().partition());
                    }
                });
        
        System.out.println("🔥 Sending Kafka Event: " + event);
        
       
        
        logger.info("Ticket created ...");

    }


    public void sendTicketResolvedEvent(String username, Long ticketId) {

        SupportEvent event = buildEvent(
                username,
                "TICKET_RESOLVED",
                "Support ticket resolved (ID: " + ticketId + ")",
                "RESOLVED"
        );

        kafkaTemplate.send(TOPIC, event);
        logger.info("Support resolved event sent for username: {}", username);
    }

    private SupportEvent buildEvent(String username,
                                    String eventType,
                                    String message,
                                    String status) {

        return new SupportEvent(
                username,
                "support",
                eventType,
                message,
                "customersupport-service",
                LocalDateTime.now(),
                status
        );
    }
}
