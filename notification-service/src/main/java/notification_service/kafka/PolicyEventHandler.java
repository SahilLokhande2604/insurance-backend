package notification_service.kafka;

import notification_service.models.Notification;
import notification_service.service.NotificationService;
import notification_service.dto.PolicyEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PolicyEventHandler {

    private final NotificationService notificationService;

    public PolicyEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void handle(PolicyEvent event) {
        if (!"policy".equalsIgnoreCase(event.getType())) return;

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
        System.out.println("POLICY NOTIFICATION SAVED");
    }
}
