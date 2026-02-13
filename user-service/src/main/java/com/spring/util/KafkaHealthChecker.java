package com.spring.util;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaHealthChecker {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String HEALTH_CHECK_TOPIC = "health-check";

    /**
     * Check if Kafka is up and running by sending a test message
     * @return true if message sent successfully, false otherwise
     */
    public boolean isKafkaUp() {
        try {
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            
            kafkaTemplate.send(HEALTH_CHECK_TOPIC, "health-check-" + System.currentTimeMillis())
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        future.complete(true);
                    } else {
                        System.err.println("Kafka health check failed: " + ex.getMessage());
                        future.complete(false);
                    }
                });

            return future.get();
        } catch (Exception e) {
            System.err.println("Error checking Kafka health: " + e.getMessage());
            return false;
        }
    }

    /**
     * Send a test message to verify Kafka connectivity
     * @param message the message to send
     * @return true if successful, false otherwise
     */
    public boolean sendTestMessage(String message) {
        try {
            kafkaTemplate.send(HEALTH_CHECK_TOPIC, message);
            return true;
        } catch (Exception e) {
            System.err.println("Error sending test message: " + e.getMessage());
            return false;
        }
    }
}
