package com.bmo.common.delivery_service.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryTypeUpdateDto {

  private ShippingMethodDto shippingMethod;

  private String name;

  private String description;

  private Boolean isAvailable;

}
