package notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Generic event coming on 'insurance' topic from other services.
 * Examples of type: "policy", "payment", "claim", "user"
 * Example JSON:
 * {
 *   "userId": 123,
 *   "type": "policy",
 *   "message": "Your policy has been created",
 *   "eventType": "POLICY_CREATED",
 *   "timestamp": "2024-01-01T12:00:00"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceEvent {
	private Long userId;
	private String type;            // Category: policy, payment, claim, user
	private String message;         // Human readable message
	private String eventType;       // Specific event type (e.g., POLICY_CREATED, CLAIM_FILED)
	private String serviceSource;   // Which service sent this (user-service, policy-service, etc.)
	private LocalDateTime timestamp;
	private String status;      
	// Status context for the event
	public InsuranceEvent() {
		super();
	}
	public InsuranceEvent(Long userId, String type, String message, String eventType, String serviceSource,
			LocalDateTime timestamp, String status) {
		super();
		this.userId = userId;
		this.type = type;
		this.message = message;
		this.eventType = eventType;
		this.serviceSource = serviceSource;
		this.timestamp = timestamp;
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getServiceSource() {
		return serviceSource;
	}
	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}