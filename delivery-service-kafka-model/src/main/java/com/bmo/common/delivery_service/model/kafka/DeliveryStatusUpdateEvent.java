package com.bmo.common.delivery_service.model.kafka;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryStatusUpdateEvent {

  private UUID deliveryId;
  private UUID orderId;
  private UUID userId;
  private DeliveryTypeSnapshotDto deliveryType;
  private DeliveryStatusDto status;
}
