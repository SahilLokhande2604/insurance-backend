package notification_service.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import notification_service.dto.InsuranceEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

@Value("${spring.kafka.bootstrap-servers}")
private String bootstrap;

@Value("${spring.kafka.consumer.group-id}")
private String groupId;

@Bean
public ConsumerFactory<String, InsuranceEvent> insuranceEventConsumerFactory() {
 JsonDeserializer<InsuranceEvent> valueDeserializer = new JsonDeserializer<>(InsuranceEvent.class);
 valueDeserializer.addTrustedPackages("*");

 Map<String, Object> props = new HashMap<>();
 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
 props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
 props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
 props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
 // Read committed only if you need transactional semantics:
 // props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

 return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), valueDeserializer);
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String, InsuranceEvent> insuranceEventKafkaListenerContainerFactory() {
 ConcurrentKafkaListenerContainerFactory<String, InsuranceEvent> factory =
     new ConcurrentKafkaListenerContainerFactory<>();
 factory.setConsumerFactory(insuranceEventConsumerFactory());
 return factory;
}
}