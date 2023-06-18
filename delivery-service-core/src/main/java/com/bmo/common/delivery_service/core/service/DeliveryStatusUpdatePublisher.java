package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.model.kafka.DeliveryStatusUpdateEvent;

public interface DeliveryStatusUpdatePublisher {

  void publish(DeliveryStatusUpdateEvent event);
}
