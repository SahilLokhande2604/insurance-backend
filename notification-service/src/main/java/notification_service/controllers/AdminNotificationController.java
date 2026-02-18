package notification_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import notification_service.models.Notification;
import notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/admin/notifications")
public class AdminNotificationController {

    private final NotificationService notificationService;

    public AdminNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // ✅ Admin sends notification to specific user
    @PostMapping("/send-to-user")
    public ResponseEntity<Notification> sendToUser(
            @RequestParam String username,
            @RequestBody Notification notification) {

        Notification sent = notificationService.sendToSpecificUser(username, notification);
        return new ResponseEntity<>(sent, HttpStatus.CREATED);
    }

    // ✅ Get all admin notifications
    @GetMapping
    public ResponseEntity<?> getAdminNotifications() {
        return ResponseEntity.ok(notificationService.getAdminNotifications());
    }
}
