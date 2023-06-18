package com.bmo.common.delivery_service.model.kafka;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryTypeSnapshotDto {

  private UUID id;

  private ShippingMethodDto shippingMethod;

  private String name;

  private String description;

  private Boolean isAvailable;

}
