//package notification_service.kafka;
//
//import notification_service.dto.PaymentEvent;
//import notification_service.models.Notification;
//import notification_service.service.NotificationService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class PaymentEventHandler {
//
//    private final NotificationService notificationService;
//
//    public PaymentEventHandler(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    public void handle(PaymentEvent event) {
//
//        if (!"payment".equalsIgnoreCase(event.getType())) {
//            return;
//        }
//
//        Notification notification = new Notification();
//
//        // If username exists use it, otherwise map userId
//        notification.setUsername(
//                event.getUsername() != null ?
//                        event.getUsername() :
//                        "User-" 
//        );
//
//        notification.setMessage(event.getMessage());
//        notification.setType(event.getType());
//        notification.setEventType(event.getEventType());
//        notification.setStatus(event.getStatus());
//        notification.setIsRead(false);
//        notification.setCreatedAt(LocalDateTime.now());
//        notification.setUpdatedAt(LocalDateTime.now());
//
//        notificationService.sendNotification(notification);
//
//        System.out.println("PAYMENT NOTIFICATION SAVED");
//    }
//}

package notification_service.kafka;

import notification_service.dto.PaymentEvent;
import notification_service.models.Notification;
import notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentEventHandler {

    private final NotificationService notificationService;

    public PaymentEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void handle(PaymentEvent event) {

        if (!"payment".equalsIgnoreCase(event.getType())) {
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

        System.out.println("PAYMENT NOTIFICATION SAVED");
    }
}

