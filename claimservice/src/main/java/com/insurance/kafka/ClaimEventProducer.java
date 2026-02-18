//package com.insurance.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class ClaimEventProducer {
//	private static final String CLAIM_TOPIC = "insurance";
//	private static final Logger logger = LoggerFactory.getLogger(ClaimEventProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	private final ObjectMapper objectMapper = new ObjectMapper();
//
//	
//	
//	public ClaimEventProducer() {
//		super();
//	}
//
//	public ClaimEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//	public void sendClaimSubmittedEvent(String username, String claimDetails) {
//		try {
//			Map<String, Object> event = buildEvent(username, "claim", "CLAIM_FILED", 
//				"Claim submitted: " + claimDetails, "PENDING");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, CLAIM_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Claim submitted event sent for userId: {}", username);
//		} catch (Exception e) {
//			logger.error("Error sending claim submitted event", e);
//		}
//	}
//
//	public void sendClaimStatusEvent(String username, String claimStatus, String details) {
//		try {
//			Map<String, Object> event = buildEvent(username, "claim", "CLAIM_STATUS_UPDATED", 
//				"Claim status: " + claimStatus + " - " + details, claimStatus);
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, CLAIM_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Claim status event sent for userId: {} with status: {}", username, claimStatus);
//		} catch (Exception e) {
//			logger.error("Error sending claim status event", e);
//		}
//	}
//
//	public void sendClaimApprovedEvent(String username, double approvedAmount) {
//		try {
//			Map<String, Object> event = buildEvent(username, "claim", "CLAIM_APPROVED", 
//				"Claim approved for amount: " + approvedAmount, "APPROVED");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, CLAIM_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Claim approved event sent for userId: {}", username);
//		} catch (Exception e) {
//			logger.error("Error sending claim approved event", e);
//		}
//	}
//
//	public void sendClaimRejectedEvent(String username, String rejectionReason) {
//		try {
//			Map<String, Object> event = buildEvent(username, "claim", "CLAIM_REJECTED", 
//				"Claim rejected: " + rejectionReason, "REJECTED");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, CLAIM_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Claim rejected event sent for userId: {}", username);
//		} catch (Exception e) {
//			logger.error("Error sending claim rejected event", e);
//		}
//	}
//
//	private Map<String, Object> buildEvent(String username, String type, String eventType, 
//											String message, String status) {
//		Map<String, Object> event = new HashMap<>();
//		event.put("username", username);
//		event.put("type", type);
//		event.put("eventType", eventType);
//		event.put("message", message);
//		event.put("serviceSource", "claim-service");
////		event.put("timestamp", LocalDateTime.now());
//		event.put("timestamp", LocalDateTime.now().toString());
//
//		event.put("status", status);
//		return event;
//	}
//}

package com.insurance.kafka;

import com.insurance.kafka.dto.ClaimEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClaimEventProducer {

    private static final String CLAIM_TOPIC = "insurance";
    private static final Logger logger =
            LoggerFactory.getLogger(ClaimEventProducer.class);

    private final KafkaTemplate<String, ClaimEvent> kafkaTemplate;

    public ClaimEventProducer(KafkaTemplate<String, ClaimEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClaimSubmittedEvent(
                                        String username,
                                        String claimDetails) {

        ClaimEvent event = buildEvent(
                username,
                "CLAIM_FILED",
                "Claim submitted: " + claimDetails,
                "PENDING"
        );

        kafkaTemplate.send(CLAIM_TOPIC, event);
        logger.info("Claim submitted event sent for username: {}", username);
    }

    public void sendClaimApprovedEvent(
                                       String username,
                                       double approvedAmount) {

        ClaimEvent event = buildEvent(
                username,
                "CLAIM_APPROVED",
                "Claim approved for amount: " + approvedAmount,
                "APPROVED"
        );

        kafkaTemplate.send(CLAIM_TOPIC, event);
        logger.info("Claim approved event sent for username: {}", username);
    }

    public void sendClaimRejectedEvent(String username,
                                       String reason) {

        ClaimEvent event = buildEvent(
                username,
                "CLAIM_REJECTED",
                "Claim rejected: " + reason,
                "REJECTED"
        );

        kafkaTemplate.send(CLAIM_TOPIC, event);
        logger.info("Claim rejected event sent for username: {}", username);
    }

    private ClaimEvent buildEvent(String username,
                                  String eventType,
                                  String message,
                                  String status) {

        return new ClaimEvent(
                username,
                "claim",
                eventType,
                message,
                "claim-service",
                LocalDateTime.now(),
                status
        );
    }
}

