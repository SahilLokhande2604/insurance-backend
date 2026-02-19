//package com.example.payment_service.kafka;
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
//public class PaymentEventProducer {
//	private static final String PAYMENT_TOPIC = "insurance";
//	private static final Logger logger = LoggerFactory.getLogger(PaymentEventProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	private final ObjectMapper objectMapper = new ObjectMapper();
//
////	public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
////		this.kafkaTemplate = kafkaTemplate;
////	}
//	
//	
//	
//
//	public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
//		super();
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//
//
//
//	public KafkaTemplate<String, String> getKafkaTemplate() {
//		return kafkaTemplate;
//	}
//
//
//
//	public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//
//
//	public static String getPaymentTopic() {
//		return PAYMENT_TOPIC;
//	}
//
//
//
//	public static Logger getLogger() {
//		return logger;
//	}
//
//
//
//	public ObjectMapper getObjectMapper() {
//		return objectMapper;
//	}
//
//
//
//	public void sendPaymentSuccessEvent(String username, String transactionId, double amount) {
//		try {
//			Map<String, Object> event = buildEvent(username, "payment", "PAYMENT_SUCCESS", 
//				String.format("Payment of Rs. %.2f completed. Transaction ID: %s", amount, transactionId), 
//				"SUCCESS");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, PAYMENT_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Payment success event sent for userId: {} with transactionId: {}", username, transactionId);
//		} catch (Exception e) {
//			logger.error("Error sending payment success event", e);
//		}
//	}
//
//	public void sendPaymentFailureEvent(String username, String transactionId, String errorMessage) {
//		try {
//			Map<String, Object> event = buildEvent(username, "payment", "PAYMENT_FAILED", 
//				String.format("Payment failed. Transaction ID: %s. Reason: %s", transactionId, errorMessage), 
//				"FAILED");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, PAYMENT_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Payment failure event sent for userId: {} with transactionId: {}", username, transactionId);
//		} catch (Exception e) {
//			logger.error("Error sending payment failure event", e);
//		}
//	}
//
//	public void sendPaymentRefundEvent(String username, String transactionId, double refundAmount) {
//		try {
//			Map<String, Object> event = buildEvent(username, "payment", "PAYMENT_REFUNDED", 
//				String.format("Refund of Rs. %.2f processed. Original Transaction ID: %s", refundAmount, transactionId), 
//				"REFUNDED");
//
//			String jsonEvent = objectMapper.writeValueAsString(event);
//			Message<String> message = MessageBuilder
//				.withPayload(jsonEvent)
//				.setHeader(KafkaHeaders.TOPIC, PAYMENT_TOPIC)
//				.build();
//
//			kafkaTemplate.send(message);
//			logger.info("Payment refund event sent for userId: {}", username);
//		} catch (Exception e) {
//			logger.error("Error sending payment refund event", e);
//		}
//	}
//
//	private Map<String, Object> buildEvent(String username, String type, String eventType, 
//											String message, String status) {
//		Map<String, Object> event = new HashMap<>();
//		event.put("userId", username);
//		event.put("type", type);
//		event.put("eventType", eventType);
//		event.put("message", message);
//		event.put("serviceSource", "payment-service");
//		event.put("timestamp", LocalDateTime.now());
//		event.put("status", status);
//		return event;
//	}
//}


package com.example.payment_service.kafka;

import com.example.payment_service.kafka.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentEventProducer {

    private static final String PAYMENT_TOPIC = "insurance";

    private static final Logger logger =
            LoggerFactory.getLogger(PaymentEventProducer.class);

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public PaymentEventProducer(
            KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // ==========================
    // PAYMENT SUCCESS
    // ==========================
    public void sendPaymentSuccessEvent(
            String username,
            String transactionId,
            double amount) {

        PaymentEvent event = buildEvent(
                username,
                "PAYMENT_SUCCESS",
                String.format(
                        "Payment of Rs. %.2f completed. Transaction ID: %s",
                        amount,
                        transactionId),
                "SUCCESS"
        );

        kafkaTemplate.send(PAYMENT_TOPIC, event);

        logger.info(
                "Payment success event sent for username: {}",
                username);
    }

    // ==========================
    // PAYMENT FAILURE
    // ==========================
    public void sendPaymentFailureEvent(
            String username,
            String transactionId,
            String errorMessage) {

        PaymentEvent event = buildEvent(
                username,
                "PAYMENT_FAILED",
                String.format(
                        "Payment failed. Transaction ID: %s. Reason: %s",
                        transactionId,
                        errorMessage),
                "FAILED"
        );

        kafkaTemplate.send(PAYMENT_TOPIC, event);

        logger.info(
                "Payment failure event sent for username: {}",
                username);
    }

    // ==========================
    // PAYMENT REFUND
    // ==========================
    public void sendPaymentRefundEvent(
            String username,
            String transactionId,
            double refundAmount) {

        PaymentEvent event = buildEvent(
                username,
                "PAYMENT_REFUNDED",
                String.format(
                        "Refund of Rs. %.2f processed. Transaction ID: %s",
                        refundAmount,
                        transactionId),
                "REFUNDED"
        );

        kafkaTemplate.send(PAYMENT_TOPIC, event);

        logger.info(
                "Payment refund event sent for username: {}",
                username);
    }

    // ==========================
    // COMMON BUILDER METHOD
    // ==========================
    private PaymentEvent buildEvent(
            String username,
            String eventType,
            String message,
            String status) {

        return new PaymentEvent(
                username,
                "payment",
                eventType,
                message,
                "payment-service",
                LocalDateTime.now(),
                status
        );
    }
}
