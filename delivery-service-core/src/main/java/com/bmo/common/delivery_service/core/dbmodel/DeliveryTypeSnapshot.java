package com.bmo.common.delivery_service.core.dbmodel;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryTypeSnapshot {

  private UUID id;

  private ShippingMethod shippingMethod;

  private String name;

  private String description;

  private Boolean isAvailable;

}
