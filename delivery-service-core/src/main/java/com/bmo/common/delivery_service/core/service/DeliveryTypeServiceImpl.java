package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.mapper.DeliveryMapper;
import com.bmo.common.delivery_service.core.repository.DeliveryRepository;
import com.bmo.common.delivery_service.core.repository.DeliveryTypeRepository;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

  private final DeliveryRepository deliveryRepository;
  private final DeliveryTypeRepository deliveryTypeRepository;

  private final DeliveryMapper deliveryMapper;

  @Override
  public Delivery getDeliveryForCurrentUserByOrderId(UUID orderId, UUID userId) {
    return deliveryRepository.findByOrderIdAndUserId(orderId, userId)
        .orElseThrow(() ->
            new EntityNotFoundException("Delivery for order not found, order id [%s] user id [%s]"
                .formatted(orderId, userId)));
  }

  @Override
  public Delivery createDelivery(UUID orderId, UUID userId, DeliveryCreateDto createDto) {
    UUID deliveryTypeId = createDto.getDeliveryTypeId();
    if (!deliveryTypeRepository.existsById(deliveryTypeId)) {
      throw new EntityNotFoundException("DeliveryType", deliveryTypeId);
    }
    Delivery newDelivery = deliveryMapper.mapFromCreateDto(orderId, userId, createDto);
    return deliveryRepository.save(newDelivery);
  }
}
