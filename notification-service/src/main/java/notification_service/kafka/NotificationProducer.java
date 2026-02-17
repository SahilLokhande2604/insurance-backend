package notification_service.kafka;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import notification_service.models.Notification;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, Notification> kafkaTemplate;
    private static final String TOPIC = "insurance";  // match your Kafka topic

    public NotificationProducer(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Notification notification) {
        kafkaTemplate.send(TOPIC, notification);
    }
}


