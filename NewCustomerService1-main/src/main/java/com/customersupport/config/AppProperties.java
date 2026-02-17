//package com.customersupport.config;
//
////src/main/java/com/insurance/support/config/AppProperties.java
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//@Data
//@ConfigurationProperties(prefix = "services")
//public class AppProperties {
// private Service policy;
//
// @Data
// public static class Service {
//     private String baseUrl;
//     private int connectTimeoutMs = 3000;
//     private int readTimeoutMs = 5000;
//	 public Service() {
//		super();
//	 }
//	 public Service(String baseUrl, int connectTimeoutMs, int readTimeoutMs) {
//		super();
//		this.baseUrl = baseUrl;
//		this.connectTimeoutMs = connectTimeoutMs;
//		this.readTimeoutMs = readTimeoutMs;
//	 }
//	 public String getBaseUrl() {
//		 return baseUrl;
//	 }
//	 public void setBaseUrl(String baseUrl) {
//		 this.baseUrl = baseUrl;
//	 }
//	 public int getConnectTimeoutMs() {
//		 return connectTimeoutMs;
//	 }
//	 public void setConnectTimeoutMs(int connectTimeoutMs) {
//		 this.connectTimeoutMs = connectTimeoutMs;
//	 }
//	 public int getReadTimeoutMs() {
//		 return readTimeoutMs;
//	 }
//	 public void setReadTimeoutMs(int readTimeoutMs) {
//		 this.readTimeoutMs = readTimeoutMs;
//	 }
//     
//     
// }
//
// public AppProperties() {
//	super();
// }
//
// public AppProperties(Service policy) {
//	super();
//	this.policy = policy;
// }
//
// public Service getPolicy() {
//	return policy;
// }
//
// public void setPolicy(Service policy) {
//	this.policy = policy;
// }
// 
//}

package com.customersupport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "services")
public class AppProperties {

    private Service policy;

    @Data
    public static class Service {
        private String baseUrl;
        private int connectTimeoutMs = 3000;
        private int readTimeoutMs = 5000;
		public Service() {
			super();
		}
		public Service(String baseUrl, int connectTimeoutMs, int readTimeoutMs) {
			super();
			this.baseUrl = baseUrl;
			this.connectTimeoutMs = connectTimeoutMs;
			this.readTimeoutMs = readTimeoutMs;
		}
		public String getBaseUrl() {
			return baseUrl;
		}
		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
		public int getConnectTimeoutMs() {
			return connectTimeoutMs;
		}
		public void setConnectTimeoutMs(int connectTimeoutMs) {
			this.connectTimeoutMs = connectTimeoutMs;
		}
		public int getReadTimeoutMs() {
			return readTimeoutMs;
		}
		public void setReadTimeoutMs(int readTimeoutMs) {
			this.readTimeoutMs = readTimeoutMs;
		}
        
        
    }

	public AppProperties() {
		super();
	}

	public AppProperties(Service policy) {
		super();
		this.policy = policy;
	}

	public Service getPolicy() {
		return policy;
	}

	public void setPolicy(Service policy) {
		this.policy = policy;
	}
    
	
    
}
