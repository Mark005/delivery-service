package com.bmo.common.delivery_service.model.rest;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryCreateDto {

  private UUID deliveryTypeId;

  private DeliveryAddressDto deliveryAddress;

  private ContactPhoneDto contactPhone;

}
