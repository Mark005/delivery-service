package com.bmo.common.delivery_service.core.service;


import com.bmo.common.delivery_service.core.configs.properties.KafkaProducerProperties;
import com.bmo.common.delivery_service.model.kafka.DeliveryStatusUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDeliveryStatusUpdatePublisher implements DeliveryStatusUpdatePublisher {

  @Qualifier("deliveryStatusUpdateEventProducerProperties")
  private final KafkaProducerProperties properties;
  private final KafkaTemplate<String, DeliveryStatusUpdateEvent> producerTemplate;

  @Override
  public void publish(DeliveryStatusUpdateEvent updateEvent) {
    producerTemplate.send(
        properties.getTopic(),
        updateEvent.getDeliveryId().toString(),
        updateEvent);
  }
}
