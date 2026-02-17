package notification_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import notification_service.models.Notification;
import notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	// POST /api/notifications/send
	@PostMapping("/send")
	public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
		Notification sent = notificationService.sendNotification(notification);
		return new ResponseEntity<>(sent, HttpStatus.CREATED);
	}

	// GET /api/notifications/user/{userId}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
	}

	// GET /api/notifications
	@GetMapping
	public ResponseEntity<List<Notification>> getAllNotifications() {
		return ResponseEntity.ok(notificationService.getAllNotifications());
	}

	// PUT /api/notifications/{id}/read
	@PutMapping("/{id}/read")
	public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
		Notification notification = notificationService.markAsRead(id);
		if (notification != null) {
			return ResponseEntity.ok(notification);
		}
		return ResponseEntity.notFound().build();
	}

	// PUT /api/notifications/user/{userId}/read-all
	@PutMapping("/user/{userId}/read-all")
	public ResponseEntity<Void> markAllAsRead(@PathVariable Long userId) {
		notificationService.markAllAsRead(userId);
		return ResponseEntity.noContent().build();
	}
}