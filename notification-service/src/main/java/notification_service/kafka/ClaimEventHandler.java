//package notification_service.kafka;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import notification_service.dto.ClaimEvent;
//import notification_service.models.Notification;
//import notification_service.service.NotificationService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class ClaimEventHandler {
//
//    private final ObjectMapper objectMapper;
//    private final NotificationService notificationService;
//
//    public ClaimEventHandler(ObjectMapper objectMapper,
//                             NotificationService notificationService) {
//        this.objectMapper = objectMapper;
//        this.notificationService = notificationService;
//    }
//
//    public void handle(JsonNode node) {
//
//        try {
//            ClaimEvent event = objectMapper.treeToValue(node, ClaimEvent.class);
//
//            Notification notification = new Notification();
//            notification.setUsername(event.getUsername());
//            notification.setMessage(event.getMessage());
//            notification.setType(event.getType());
//            notification.setEventType(event.getEventType());
//            notification.setStatus(event.getStatus());
//            notification.setIsRead(false);
//            notification.setCreatedAt(LocalDateTime.now());
//            notification.setUpdatedAt(LocalDateTime.now());
//
//            notificationService.sendNotification(notification);
//
//            System.out.println("CLAIM NOTIFICATION SAVED");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package notification_service.kafka;

import notification_service.dto.ClaimEvent;
import notification_service.models.Notification;
import notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClaimEventHandler {

    private final NotificationService notificationService;

    public ClaimEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void handle(ClaimEvent event) {

        if (!"claim".equalsIgnoreCase(event.getType())) {
            return;
        }

        Notification notification = new Notification();
        notification.setUsername(event.getUsername());
        notification.setMessage(event.getMessage());
        notification.setType(event.getType());
        notification.setEventType(event.getEventType());
        notification.setStatus(event.getStatus());
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        notificationService.sendNotification(notification);

        System.out.println("CLAIM NOTIFICATION SAVED");
    }
}
