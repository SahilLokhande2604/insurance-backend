//package notification_service.kafka;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
////@Service
////public class GenericEventListener {
////
////    private final ObjectMapper objectMapper;
////    private final ClaimEventHandler claimEventHandler;
////    private final UserEventHandler userEventHandler;
//////    private final PolicyEventHandler policyEventHandler;
////
////    public GenericEventListener(
////            ObjectMapper objectMapper,
////            ClaimEventHandler claimEventHandler,
////            UserEventHandler userEventHandler
////            ) {
////
////        this.objectMapper = objectMapper;
////        this.claimEventHandler = claimEventHandler;
////        this.userEventHandler = userEventHandler;
//////        this.policyEventHandler = policyEventHandler;
////    }
////
////    @KafkaListener(
////            topics = "insurance",
////            groupId = "notification-service"
////    )
////    public void handleEvent(String message) {
////
////        try {
////            JsonNode node = objectMapper.readTree(message);
////            String type = node.get("type").asText();
////
////            switch (type.toLowerCase()) {
////
////                case "claim":
////                    claimEventHandler.handle(node);
////                    break;
////
////                case "user":
////                    userEventHandler.handle(node);
////                    break;
////
//////                case "policy":
//////                    policyEventHandler.handle(node);
//////                    break;
////
////                default:
////                    System.out.println("Unknown event type received: " + type);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////}
////.
//
//@Service
//public class GenericEventListener {
//
//    private final ObjectMapper objectMapper;
//    private final ClaimEventHandler claimEventHandler;
//    private final UserEventHandler userEventHandler;
//
//    public GenericEventListener(
//            ObjectMapper objectMapper,
//            ClaimEventHandler claimEventHandler,
//            UserEventHandler userEventHandler) {
//
//        this.objectMapper = objectMapper;
//        this.claimEventHandler = claimEventHandler;
//        this.userEventHandler = userEventHandler;
//    }
//
//    @KafkaListener(
//            topics = "insurance",
//            groupId = "notification-service"
//    )
//    public void handleEvent(String message) {
//
//        try {
//            JsonNode node = objectMapper.readTree(message);
//            String type = node.get("type").asText();
//
//            switch (type.toLowerCase()) {
//
//                case "claim":
//                    claimEventHandler.handle(node);
//                    break;
//
//                case "user":
//                    userEventHandler.handle(node);
//                    break;
//
//                default:
//                    System.out.println("Unknown event type received: " + type);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//

package notification_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification_service.dto.ClaimEvent;
import notification_service.dto.PolicyEvent;
import notification_service.dto.UserEvent;
import notification_service.dto.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import notification_service.dto.SupportEvent;


@Service
public class GenericEventListener {

    private final ObjectMapper objectMapper;
    private final ClaimEventHandler claimEventHandler;
    private final UserEventHandler userEventHandler;

 // Add PolicyEventHandler to constructor
    private final PolicyEventHandler policyEventHandler;
    
    private final PaymentEventHandler paymentEventHandler;
    
    private final SupportEventHandler supportEventHandler;



    public GenericEventListener(
            ObjectMapper objectMapper,
            ClaimEventHandler claimEventHandler,
            UserEventHandler userEventHandler,
            PolicyEventHandler policyEventHandler,
            PaymentEventHandler paymentEventHandler,
            SupportEventHandler supportEventHandler) {

        this.objectMapper = objectMapper;
        this.claimEventHandler = claimEventHandler;
        this.userEventHandler = userEventHandler;
        this.policyEventHandler = policyEventHandler;
        this.paymentEventHandler = paymentEventHandler;
        this.supportEventHandler = supportEventHandler;
    }



//    @KafkaListener(topics = "insurance", groupId = "notification-service")
//    public void handleEvent(String message) {
//        try {
//            String type = objectMapper.readTree(message).get("type").asText();
//
//            switch (type.toLowerCase()) {
//                case "claim":
//                    claimEventHandler.handle(objectMapper.readValue(message, ClaimEvent.class));
//                    break;
//                case "user":
//                    userEventHandler.handle(objectMapper.readValue(message, UserEvent.class));
//                    break;
//                case "policy":
//                    policyEventHandler.handle(objectMapper.readValue(message, PolicyEvent.class));
//                    break;
//                default:
//                    System.out.println("Unknown event type: " + type);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    @KafkaListener(topics = "insurance", groupId = "notification-service")
    public void handleEvent(String message) {

        try {
            String type = objectMapper.readTree(message)
                                      .get("type")
                                      .asText();

            switch (type.toLowerCase()) {

                case "claim":
                    claimEventHandler.handle(
                            objectMapper.readValue(message, ClaimEvent.class));
                    break;

                case "user":
                    userEventHandler.handle(
                            objectMapper.readValue(message, UserEvent.class));
                    break;

                case "policy":
                    policyEventHandler.handle(
                            objectMapper.readValue(message, PolicyEvent.class));
                    break;

                case "payment":
                    paymentEventHandler.handle(
                            objectMapper.readValue(message, PaymentEvent.class));
                    break;
                    
                case "support":
                    supportEventHandler.handle(
                            objectMapper.readValue(message, SupportEvent.class));
                    break;


                default:
                    System.out.println("Unknown event type: " + type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

