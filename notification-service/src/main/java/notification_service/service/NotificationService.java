package notification_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import notification_service.models.Notification;
import notification_service.repositories.NotificationRepository;
import notification_service.kafka.NotificationProducer;

@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;
//	private final NotificationProducer notificationProducer;

	

public NotificationService(NotificationRepository notificationRepository
			) {
		super();
		this.notificationRepository = notificationRepository;
//		this.notificationProducer = notificationProducer;
	}

//	@Transactional
//	public Notification sendNotification(Notification notification) {
//		// Mark as SENT and ensure is_read is false
//		notification.setStatus("SENT");
//		if (notification.getIsRead() == null) {
//			notification.setIsRead(false);
//		}
//		if (notification.getCreatedAt() == null) {
//			notification.setCreatedAt(LocalDateTime.now());
//		}
//		if (notification.getUpdatedAt() == null) {
//			notification.setUpdatedAt(LocalDateTime.now());
//		}
//		return notificationRepository.save(notification);
//	}
	
//	@Transactional
//    public Notification sendNotification(Notification notification) {
//        // Save to DB
//        notification.setStatus("SENT");
//        if (notification.getIsRead() == null) notification.setIsRead(false);
//        if (notification.getCreatedAt() == null) notification.setCreatedAt(LocalDateTime.now());
//        if (notification.getUpdatedAt() == null) notification.setUpdatedAt(LocalDateTime.now());
//
//        Notification saved = notificationRepository.save(notification);
//
//        // Publish to Kafka
//        notificationProducer.sendNotification(saved);
//
//        return saved;
//    }

@Transactional
public Notification sendNotification(Notification notification) {

    if (notification.getIsRead() == null)
        notification.setIsRead(false);

    if (notification.getCreatedAt() == null)
        notification.setCreatedAt(LocalDateTime.now());

    notification.setUpdatedAt(LocalDateTime.now());

    Notification saved = notificationRepository.save(notification);

    System.out.println("SAVED IN DB -> ID: " + saved.getId());

    return saved;
}



	@Transactional
	public Notification markAsRead(Long notificationId) {
		Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
		if (optionalNotification.isPresent()) {
			Notification notification = optionalNotification.get();
			notification.setIsRead(true);
			notification.setUpdatedAt(LocalDateTime.now());
			return notificationRepository.save(notification);
		}
		return null;
	}

	@Transactional
	public void markAllAsRead(String username) {
		List<Notification> notifications = notificationRepository.findByUsername(username);
		for (Notification notification : notifications) {
			if (!notification.getIsRead()) {
				notification.setIsRead(true);
				notification.setUpdatedAt(LocalDateTime.now());
				notificationRepository.save(notification);
			}
		}
	}

	@Transactional(readOnly = true)
	public List<Notification> getNotificationsByUser(String username) {
		return notificationRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public List<Notification> getAllNotifications() {
		return notificationRepository.findAll();
	}
}