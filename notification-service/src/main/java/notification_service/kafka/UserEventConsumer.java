package notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import notification_service.dto.UserEvent;
import notification_service.models.Notification;
import notification_service.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    @KafkaListener(topics = "insurance", groupId = "notification-service")
//    public void consume(String message) {
//        try {
//            // Convert JSON string to Map
//            Map<String, Object> event = objectMapper.readValue(message, Map.class);
//
//            // Only process USER events
//            if ("user".equals(event.get("type"))) {
//
//                Notification notification = new Notification();
//                notification.setUserId(Long.valueOf(event.get("userId").toString()));
//                notification.setMessage(event.get("message").toString());
//                notification.setType("user");
//                notification.setStatus("SENT");
//                notification.setEventType(event.get("eventType").toString());
//                notification.setIsRead(false);
//
//                notificationService.sendNotification(notification);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    @KafkaListener(topics = "insurance", groupId = "notification-service")
//    public void consume(String message) {
//        try {
//            System.out.println("Received message: " + message);
//
//            Map<String, Object> event = objectMapper.readValue(message, Map.class);
//
//            if ("user".equals(event.get("type"))) {
//
//                Notification notification = new Notification();
//
//                notification.setUserId(Long.valueOf(event.get("userId").toString()));
//                notification.setMessage(event.get("message").toString());
//                notification.setType("user");
//                notification.setStatus(event.get("status").toString());
//                notification.setEventType(event.get("eventType").toString());
//                notification.setIsRead(false);
//
//                notification.setCreatedAt(LocalDateTime.now());
//                notification.setUpdatedAt(LocalDateTime.now());
//
//                notificationService.sendNotification(notification);
//
//                System.out.println("Notification saved successfully!");
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    @KafkaListener(topics = "insurance", groupId = "notification-service")
    public void consume(UserEvent event) {

        if ("user".equals(event.getType())) {

            Notification notification = new Notification();

            notification.setUserId(event.getUserId());
            notification.setMessage(event.getMessage());
            notification.setType("user");
            notification.setStatus("SENT");
            notification.setEventType(event.getEventType());
            notification.setIsRead(false);

            notificationService.sendNotification(notification);

            System.out.println("Notification saved successfully!");
        }
    }
    
    @KafkaListener(
            topics = "insurance",
            groupId = "notification-service"
    )
    public void onMessage(String message) {

        System.out.println("RAW MESSAGE RECEIVED: " + message);

        Notification n = new Notification();
        n.setUserId(0L);
        n.setMessage("User register successfully");
        n.setType("user");
        n.setStatus("SENT");
        n.setIsRead(false);
        n.setCreatedAt(LocalDateTime.now());
        n.setUpdatedAt(LocalDateTime.now());

        notificationService.sendNotification(n);
    }



}
