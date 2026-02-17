package notification_service.dto;

import lombok.Data;

@Data
public class UserEvent {

    private Long userId;
    private String type;
    private String eventType;
    private String message;
    private String serviceSource;
    private String timestamp;
    private String status;
	public UserEvent() {
		super();
	}
	public UserEvent(Long userId, String type, String eventType, String message, String serviceSource, String timestamp,
			String status) {
		super();
		this.userId = userId;
		this.type = type;
		this.eventType = eventType;
		this.message = message;
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getServiceSource() {
		return serviceSource;
	}
	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}

