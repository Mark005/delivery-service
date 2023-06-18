package com.bmo.common.delivery_service.core.configs;

import com.bmo.common.delivery_service.core.configs.properties.KafkaClusterProperties;
import com.bmo.common.delivery_service.core.configs.properties.KafkaProducerProperties;
import com.bmo.common.delivery_service.model.kafka.DeliveryStatusUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

  @Bean
  @ConfigurationProperties("kafka.producer.delivery-status-event")
  public KafkaProducerProperties deliveryStatusUpdateEventProducerProperties() {
    return new KafkaProducerProperties();
  }

  @Bean
  public KafkaTemplate<String, DeliveryStatusUpdateEvent> deliveryStatusUpdateEventKafkaTemplate(
      KafkaProducerProperties deliveryStatusUpdateEventProducerProperties,
      ObjectMapper objectMapper) {
    return new KafkaTemplate<>(
        new DefaultKafkaProducerFactory<>(
            buildProducerConfigs(deliveryStatusUpdateEventProducerProperties),
            new StringSerializer(),
            new JsonSerializer<>(objectMapper)));
  }

  private Map<String, Object> buildProducerConfigs(KafkaProducerProperties producerProperties) {
    KafkaClusterProperties clusterProperties = producerProperties.getCluster();

    Map<String, Object> clusterConfigs = Map.of(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, clusterProperties.getBootstrapServers()
    );
    Map<String, Object> securityConfigs = Map.of();
    Map<String, Object> trustStoreConfigs = Map.of();

    return Stream.of(clusterConfigs, securityConfigs, trustStoreConfigs)
        .flatMap(m -> m.entrySet().stream())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
