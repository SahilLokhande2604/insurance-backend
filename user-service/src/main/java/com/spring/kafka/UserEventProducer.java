//package com.spring.kafka;
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
//public class UserEventProducer {
//	private static final String USER_TOPIC = "insurance";
//	private static final Logger logger = LoggerFactory.getLogger(UserEventProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	private final ObjectMapper objectMapper = new ObjectMapper();
//
//	public UserEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//	public void sendUserRegistrationEvent(Long userId, String userName, String email) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "user", "USER_REGISTERED", 
//				String.format("User registered: %s (%s)", userName, email), "ACTIVE");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, USER_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("User registration event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending user registration event", e);
//		}
//	}
//
//	public void sendUserProfileUpdatedEvent(Long userId, String updateDetails) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "user", "USER_PROFILE_UPDATED", 
//				"User profile updated: " + updateDetails, "ACTIVE");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, USER_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("User profile updated event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending user profile updated event", e);
//		}
//	}
//
//	public void sendUserLoginEvent(Long userId) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "user", "USER_LOGIN", 
//				"User logged in", "ACTIVE");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, USER_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("User login event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending user login event", e);
//		}
//	}
//
//	public void sendUserDeactivatedEvent(Long userId, String reason) {
//		try {
//			Map<String, Object> event = buildEvent(userId, "user", "USER_DEACTIVATED", 
//				"User account deactivated: " + reason, "INACTIVE");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, USER_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("User deactivated event sent for userId: {}", userId);
//		} catch (Exception e) {
//			logger.error("Error sending user deactivated event", e);
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
//		event.put("serviceSource", "user-service");
////		event.put("timestamp", LocalDateTime.now());
//		event.put("timestamp", LocalDateTime.now().toString());
//
//		event.put("status", status);
//		return event;
//	}
//}


package com.spring.kafka;

import com.spring.kafka.dto.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserEventProducer {

    private static final String USER_TOPIC = "insurance";
    private static final Logger logger = LoggerFactory.getLogger(UserEventProducer.class);

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserRegistrationEvent(String username, String userName, String email) {

        UserEvent event = buildEvent(
                username,
                "USER_REGISTERED",
                "User registered: " + userName + " (" + email + ")",
                "ACTIVE"
        );

        kafkaTemplate.send(USER_TOPIC, event);
        logger.info("User registration event sent for username: {}", username);
    }

    public void sendUserLoginEvent(String username) {

        UserEvent event = buildEvent(
                username,
                "USER_LOGIN",
                "User logged in",
                "ACTIVE"
        );

        kafkaTemplate.send(USER_TOPIC, event);
        logger.info("User login event sent for username: {}", username);
    }

    public void sendUserDeactivatedEvent(String username, String reason) {

        UserEvent event = buildEvent(
                username,
                "USER_DEACTIVATED",
                "User account deactivated: " + reason,
                "INACTIVE"
        );

        kafkaTemplate.send(USER_TOPIC, event);
        logger.info("User deactivated event sent for username: {}", username);
    }

    private UserEvent buildEvent(String username,
                                 String eventType,
                                 String message,
                                 String status) {

        return new UserEvent(
                username,
                "user",
                eventType,
                message,
                "user-service",
                LocalDateTime.now(),
                status
        );
    }
}
