package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import java.util.UUID;

public interface DeliveryService {

  Delivery getDeliveryForCurrentUserByOrderId(UUID orderId, UUID userId);

  Delivery createDelivery(UUID orderId, UUID userId, DeliveryCreateDto createDto);

}
