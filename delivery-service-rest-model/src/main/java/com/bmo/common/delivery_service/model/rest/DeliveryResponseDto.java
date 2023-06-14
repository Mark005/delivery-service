package com.bmo.common.delivery_service.model.rest;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryResponseDto {

  private UUID id;

  private UUID orderId;

  private UUID userId;

  private DeliveryTypeResponseDto deliveryType;

  private DeliveryStatusDto status;

  private DeliveryAddressDto deliveryAddress;

  private ContactPhoneDto contactPhone;

  private List<DeliverySnapshotDto> deliveryHistory;

}
