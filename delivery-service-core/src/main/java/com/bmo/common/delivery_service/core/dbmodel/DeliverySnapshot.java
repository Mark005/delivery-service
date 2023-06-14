package com.bmo.common.delivery_service.core.dbmodel;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliverySnapshot {

  private OffsetDateTime updateDateTime;

  private UUID orderId;

  private UUID userId;

  private DeliveryTypeSnapshot deliveryType;

  private DeliveryStatus status;

  private DeliveryAddress deliveryAddress;

  private ContactPhone contactPhone;

}
