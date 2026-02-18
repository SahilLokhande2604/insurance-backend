////package com.policy.kafka;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.kafka.core.KafkaTemplate;
////import org.springframework.stereotype.Service;
////
////@Service
////public class PolicyEventProducer {
////    private static final String POLICY_TOPIC = "policy_topic";
////
////    @Autowired
////    private KafkaTemplate<String, String> kafkaTemplate;
//////    private final KafkaTemplate<String, String> kafkaTemplate;
////
////    public PolicyEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
////        this.kafkaTemplate = kafkaTemplate;
////    }
////
////
////    public void sendPolicyPurchaseEvent(String message) {
////        kafkaTemplate.send(POLICY_TOPIC, message);
////    }
////
////    public void sendPolicyUpdateEvent(String message) {
////        kafkaTemplate.send(POLICY_TOPIC, message);
////    }
////}
//
//package com.policy.kafka;
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
//public class PolicyEventProducer {
//	private static final String POLICY_TOPIC = "insurance";
//	private static final Logger logger = LoggerFactory.getLogger(PolicyEventProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	private final ObjectMapper objectMapper = new ObjectMapper();
//
//	public PolicyEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//	public void sendPolicyPurchaseEvent(Long userId, String policyDetails) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "policy", "POLICY_CREATED", 
//				"Policy purchased: " + policyDetails, "SUCCESS");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, POLICY_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Policy purchase event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending policy purchase event", e);
//		}
//	}
//
//	public void sendPolicyUpdateEvent(Long userId, String updateDetails) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "policy", "POLICY_UPDATED", 
//				"Policy updated: " + updateDetails, "SUCCESS");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, POLICY_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Policy update event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending policy update event", e);
//		}
//	}
//
//	public void sendPolicyCancelledEvent(Long userId, String cancelReason) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "policy", "POLICY_CANCELLED", 
//				"Policy cancelled: " + cancelReason, "CANCELLED");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, POLICY_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Policy cancelled event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending policy cancelled event", e);
//		}
//	}
//
//	private Map<String, Object> buildEvent(Long userId, String type, String eventType, 
//											String message, String status) {
//		Map<String, Object> event = new HashMap<>();
//		event.put("userId", userId);
//		event.put("type", type);
//		event.put("eventType", eventType);
//		event.put("message", message);
//		event.put("serviceSource", "policy-service");
//		event.put("timestamp", LocalDateTime.now());
//		event.put("status", status);
//		return event;
//	}
//}
//
//package com.policy.kafka;
//
//import com.policy.kafka.dto.PolicyEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class PolicyEventProducer {
//
//    private static final String POLICY_TOPIC = "insurance";
//    private static final Logger logger = LoggerFactory.getLogger(PolicyEventProducer.class);
//
//    private final KafkaTemplate<String, PolicyEvent> kafkaTemplate;
//
//    public PolicyEventProducer(KafkaTemplate<String, PolicyEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    
//    
//
//    public void sendPolicyCreatedEvent(String policyName, Long policyId) {
//        PolicyEvent event = buildEvent("POLICY_CREATED",
//                "New policy created: " + policyName + " (ID: " + policyId + ")", "ACTIVE");
//        kafkaTemplate.send(POLICY_TOPIC, event);
////        logger.info("Policy created event sent for username: {}");
//    }
//
//    public void sendPolicyUpdatedEvent(String policyName, Long policyId) {
//        PolicyEvent event = buildEvent( "POLICY_UPDATED",
//                "Policy updated: " + policyName + " (ID: " + policyId + ")", "ACTIVE");
//        kafkaTemplate.send(POLICY_TOPIC, event);
////        logger.info("Policy updated event sent for username: {}", username);
//    }
//
//    private PolicyEvent buildEvent(String eventType, String message, String status) {
//        return new PolicyEvent(
//                "policy",
//                eventType,
//                message,
//                "policy-service",
//                LocalDateTime.now(),
//                status
//        );
//    }
//}
//

package com.policy.kafka;

import com.policy.kafka.dto.PolicyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PolicyEventProducer {

    private static final String POLICY_TOPIC = "insurance";
    private static final Logger logger = LoggerFactory.getLogger(PolicyEventProducer.class);

    private final KafkaTemplate<String, PolicyEvent> kafkaTemplate;

    public PolicyEventProducer(KafkaTemplate<String, PolicyEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPolicyCreatedEvent(String username, String policyName, Long policyId) {
        PolicyEvent event = buildEvent(username, "POLICY_CREATED",
                "New policy created: " + policyName + " (ID: " + policyId + ")", "ACTIVE");
        kafkaTemplate.send(POLICY_TOPIC, event);
        logger.info("Policy created event sent for username: {}", username);
    }

    public void sendPolicyUpdatedEvent(String username, String policyName, Long policyId) {
        PolicyEvent event = buildEvent(username, "POLICY_UPDATED",
                "Policy updated: " + policyName + " (ID: " + policyId + ")", "ACTIVE");
        kafkaTemplate.send(POLICY_TOPIC, event);
        logger.info("Policy updated event sent for username: {}", username);
    }

    private PolicyEvent buildEvent(String username, String eventType, String message, String status) {
        return new PolicyEvent(
                username,
                "policy",
                eventType,
                message,
                "policy-service",
                LocalDateTime.now(),
                status
        );
    }
}

