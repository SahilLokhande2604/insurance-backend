package notification_service.dto;

import java.time.LocalDateTime;

public class PaymentEvent {

//    private Long userId;
    private String username;   // optional if you add later
    private String type;
    private String eventType;
    private String message;
    private String serviceSource;
    private LocalDateTime timestamp;
    private String status;

    public PaymentEvent() {}
    

public PaymentEvent(String username, String type, String eventType, String message, String serviceSource,
			LocalDateTime timestamp, String status) {
		super();
		this.username = username;
		this.type = type;
		this.eventType = eventType;
		this.message = message;
		this.serviceSource = serviceSource;
		this.timestamp = timestamp;
		this.status = status;
	}


//    public Long getUserId() { return userId; }
//    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getServiceSource() { return serviceSource; }
    public void setServiceSource(String serviceSource) { this.serviceSource = serviceSource; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

