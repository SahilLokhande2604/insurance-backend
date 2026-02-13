package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.util.KafkaHealthChecker;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @Autowired
    private KafkaHealthChecker kafkaHealthChecker;

    @GetMapping("/kafka")
    public ResponseEntity<Map<String, Object>> checkKafkaHealth() {
        Map<String, Object> response = new HashMap<>();
        boolean isKafkaUp = kafkaHealthChecker.isKafkaUp();
        
        response.put("status", isKafkaUp ? "UP" : "DOWN");
        response.put("service", "Kafka");
        response.put("timestamp", System.currentTimeMillis());
        
        if (isKafkaUp) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(503).body(response);
        }
    }

    @GetMapping("/kafka/test")
    public ResponseEntity<Map<String, String>> sendKafkaTestMessage() {
        Map<String, String> response = new HashMap<>();
        boolean success = kafkaHealthChecker.sendTestMessage("test-message-" + System.currentTimeMillis());
        
        response.put("message", success ? "Test message sent successfully" : "Failed to send test message");
        response.put("status", success ? "SUCCESS" : "FAILED");
        
        if (success) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(503).body(response);
        }
    }
}
