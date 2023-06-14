package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.model.rest.ContactPhoneDto;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryStatusDto;
import java.util.UUID;

public interface DeliveryService {

  Delivery getDeliveryByUserIsAndOrderId(UUID orderId, UUID userId);

  Delivery createDelivery(UUID orderId, UUID userId, DeliveryCreateDto createDto);

  Delivery updateDeliveryStatus(UUID orderId, UUID userId, DeliveryStatusDto status);

  Delivery updateDeliveryType(UUID orderId, UUID userId, UUID newDeliveryTypeId);

  Delivery updateDeliveryAddress(UUID orderId, UUID userId, DeliveryAddressDto newAddress);

  Delivery updateContactPhone(UUID orderId, UUID userId, ContactPhoneDto newPhone);
}
