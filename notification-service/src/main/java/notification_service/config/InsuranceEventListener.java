package notification_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import notification_service.dto.InsuranceEvent;
import notification_service.models.Notification;
import notification_service.service.NotificationService;

//@Component
//public class InsuranceEventListener {
//
//	private static final Logger logger = LoggerFactory.getLogger(InsuranceEventListener.class);
//
//	private final NotificationService notificationService;
//
//	@Value("${app.kafka.topic.insurance:insurance}")
//	private String insuranceTopic;
//
//	public InsuranceEventListener(NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}
//
//	@KafkaListener(
//		topics = "${app.kafka.topic.insurance:insurance}",
//		groupId = "${spring.kafka.consumer.group-id}",
//		containerFactory = "insuranceEventKafkaListenerContainerFactory"
//	)
//	public void onMessage(InsuranceEvent event) {
//		try {
//			logger.info("Received insurance event: type={}, userId={}, eventType={}", 
//				event.getType(), event.getUserId(), event.getEventType());
//
//			// Map event -> Notification and persist
//			Notification n = new Notification();
//			n.setUserId(event.getUserId() != null ? event.getUserId() : 0L);
//			n.setType(event.getType() != null ? event.getType() : "in-app");
//			n.setMessage(event.getMessage() != null ? event.getMessage() : "Notification received");
//			n.setEventType(event.getEventType());
//			n.setStatus("SENT");
//			n.setIsRead(false);
//			n.setCreatedAt(event.getTimestamp() != null ? event.getTimestamp() : LocalDateTime.now());
//			n.setUpdatedAt(LocalDateTime.now());
//
//			notificationService.sendNotification(n);
//			logger.info("Notification saved successfully for userId: {}", event.getUserId());
//		} catch (Exception e) {
//			logger.error("Error processing insurance event", e);
//			throw new RuntimeException("Failed to process insurance event", e);
//		}
//	}
//}

@Component
public class InsuranceEventListener {

    private static final Logger logger =
            LoggerFactory.getLogger(InsuranceEventListener.class);

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public InsuranceEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.insurance:insurance}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
//    public void onMessage(String message) {
//
//        try {
//            System.out.println("RAW MESSAGE RECEIVED: " + message);
//
//            Map<String, Object> event =
//                    objectMapper.readValue(message, Map.class);
//
//            Notification n = new Notification();
//
//            // ✅ Always safe conversions
//            n.setUserId(Long.valueOf(event.get("userId").toString()));
//            n.setType(event.get("type").toString());
//            n.setMessage(event.get("message").toString());
//            n.setEventType(event.get("eventType").toString());
//            n.setStatus(event.get("status").toString());
//
//            n.setIsRead(false);
//            n.setCreatedAt(LocalDateTime.now());
//            n.setUpdatedAt(LocalDateTime.now());
//
//            notificationService.sendNotification(n);
//
//            System.out.println("SAVED CLAIM NOTIFICATION SUCCESSFULLY");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    @KafkaListener(
            topics = "${app.kafka.topic.insurance:insurance}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onMessage(String message) {

        try {
            System.out.println("RAW MESSAGE RECEIVED: " + message);

            Map<String, Object> event =
                    objectMapper.readValue(message, Map.class);

            Notification n = new Notification();

            // ✅ Dynamic mapping from Kafka event
            n.setUserId(Long.valueOf(event.get("userId").toString()));
            n.setType(event.get("type").toString());          // claim / policy / user
            n.setEventType(event.get("eventType").toString());
            n.setMessage(event.get("message").toString());
            n.setStatus(event.get("status").toString());

            n.setIsRead(false);
            n.setCreatedAt(LocalDateTime.now());
            n.setUpdatedAt(LocalDateTime.now());

            notificationService.sendNotification(n);

            System.out.println("SAVED NOTIFICATION TYPE: " + n.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
