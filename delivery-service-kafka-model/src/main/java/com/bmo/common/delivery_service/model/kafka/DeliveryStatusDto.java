package com.bmo.common.delivery_service.model.kafka;

public enum DeliveryStatusDto {

  PAYMENT_PENDING,
  PAID,
  ACCEPTED,
  READY_TO_TAKEAWAY,
  IN_DELIVERY,
  LOST,
  CANCELED,
  DELIVERED
}
