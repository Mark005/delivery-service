package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.ContactPhone;
import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryAddress;
import com.bmo.common.delivery_service.core.dbmodel.DeliverySnapshot;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryStatus;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryTypeSnapshot;
import com.bmo.common.delivery_service.core.dbmodel.ShippingMethod;
import com.bmo.common.delivery_service.core.mapper.ContactPhoneMapper;
import com.bmo.common.delivery_service.core.mapper.DeliveryAddressMapper;
import com.bmo.common.delivery_service.core.mapper.DeliveryMapper;
import com.bmo.common.delivery_service.core.mapper.DeliveryStatusUpdateEventMapper;
import com.bmo.common.delivery_service.core.mapper.DeliveryTypeMapper;
import com.bmo.common.delivery_service.core.mapper.EnumMapper;
import com.bmo.common.delivery_service.core.repository.DeliveryRepository;
import com.bmo.common.delivery_service.core.repository.DeliveryTypeRepository;
import com.bmo.common.delivery_service.model.kafka.DeliveryStatusUpdateEvent;
import com.bmo.common.delivery_service.model.rest.ContactPhoneDto;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryStatusDto;
import com.bmo.common.delivery_service.model.rest.exception.DeliveryServiceBusinessException;
import com.bmo.common.delivery_service.model.rest.exception.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

  private final DeliveryRepository deliveryRepository;
  private final DeliveryTypeRepository deliveryTypeRepository;
  private final DeliveryStatusUpdatePublisher deliveryStatusUpdatePublisher;

  private final DeliveryMapper deliveryMapper;
  private final DeliveryTypeMapper deliveryTypeMapper;
  private final DeliveryAddressMapper deliveryAddressMapper;
  private final ContactPhoneMapper contactPhoneMapper;
  private final EnumMapper enumMapper;

  private final DeliveryStatusUpdateEventMapper updateEventMapper;

  private final Set<DeliveryStatus> UPDATE_DELIVERY_ADDRESS_ALLOWED_STATUSES =
      Set.of(DeliveryStatus.PAYMENT_PENDING,
          DeliveryStatus.PAID,
          DeliveryStatus.ACCEPTED);

  private final Set<DeliveryStatus> UPDATE_CONTACT_PHONE_ALLOWED_STATUSES =
      Set.of(DeliveryStatus.PAYMENT_PENDING,
          DeliveryStatus.PAID,
          DeliveryStatus.ACCEPTED,
          DeliveryStatus.IN_DELIVERY);

  @Override
  public Delivery getDeliveryByUserIsAndOrderId(UUID orderId, UUID userId) {
    return deliveryRepository.findByOrderIdAndUserId(orderId, userId)
        .orElseThrow(() ->
            new EntityNotFoundException("Delivery for order not found, order id [%s] user id [%s]"
                .formatted(orderId, userId)));
  }

  @Override
  public Delivery createDelivery(UUID orderId, UUID userId, DeliveryCreateDto createDto) {
    DeliveryType deliveryType = getDeliveryTypeAndCheckAvailability(createDto.getDeliveryTypeId());

    if (deliveryType.getShippingMethod() == ShippingMethod.POST && createDto.getDeliveryAddress() == null) {
      throw new DeliveryServiceBusinessException("Address can not be empty for POST shipping method");
    }

    Delivery newDelivery = deliveryMapper.mapFromCreateDto(orderId, userId, deliveryType, createDto);
    newDelivery.setStatus(DeliveryStatus.PAYMENT_PENDING);

    addConditionAfterChange(newDelivery);

    Delivery saved = deliveryRepository.save(newDelivery);
    DeliveryStatusUpdateEvent updateEvent = updateEventMapper.mapToEvent(newDelivery);
    deliveryStatusUpdatePublisher.publish(updateEvent);
    return saved;
  }

  @Override
  public Delivery updateDeliveryStatus(UUID orderId, UUID userId, DeliveryStatusDto status) {
    Delivery delivery = getDeliveryByUserIsAndOrderId(orderId, userId);
    DeliveryStatus newStatus = enumMapper.map(status);

    if (delivery.getStatus() == newStatus) {
      throw new DeliveryServiceBusinessException("Delivery already in this status");
    }

    delivery.setStatus(newStatus);
    addConditionAfterChange(delivery);

    Delivery saved = deliveryRepository.save(delivery);

    DeliveryStatusUpdateEvent updateEvent = updateEventMapper.mapToEvent(saved);
    deliveryStatusUpdatePublisher.publish(updateEvent);
    return saved;
  }

  @Override
  public Delivery updateDeliveryType(UUID orderId, UUID userId, UUID newDeliveryTypeId) {
    DeliveryType newDeliveryType = getDeliveryTypeAndCheckAvailability(newDeliveryTypeId);

    Delivery delivery = getDeliveryByUserIsAndOrderId(orderId, userId);

    if (delivery.getStatus() != DeliveryStatus.PAYMENT_PENDING) {
      throw new DeliveryServiceBusinessException("Can't update Delivery Type on this stage");
    }

    if (newDeliveryType.getShippingMethod() == ShippingMethod.TAKE_AWAY) {
      delivery.setDeliveryAddress(null);
    }

    DeliveryTypeSnapshot deliveryTypeSnapshot = deliveryTypeMapper.mapToSnapshot(newDeliveryType);
    delivery.setDeliveryType(deliveryTypeSnapshot);
    addConditionAfterChange(delivery);
    return deliveryRepository.save(delivery);
  }

  @Override
  public Delivery updateDeliveryAddress(UUID orderId, UUID userId, DeliveryAddressDto newAddress) {
    Delivery delivery = getDeliveryByUserIsAndOrderId(orderId, userId);

    if (!UPDATE_DELIVERY_ADDRESS_ALLOWED_STATUSES.contains(delivery.getStatus())) {
      throw new DeliveryServiceBusinessException("Can't update Delivery Address on this stage");
    }

    DeliveryAddress deliveryAddress = deliveryAddressMapper.map(newAddress);
    delivery.setDeliveryAddress(deliveryAddress);
    addConditionAfterChange(delivery);
    return deliveryRepository.save(delivery);
  }

  @Override
  public Delivery updateContactPhone(UUID orderId, UUID userId, ContactPhoneDto newPhone) {
    Delivery delivery = getDeliveryByUserIsAndOrderId(orderId, userId);

    if (!UPDATE_CONTACT_PHONE_ALLOWED_STATUSES.contains(delivery.getStatus())) {
      throw new DeliveryServiceBusinessException("Can't update Delivery Address on this stage");
    }

    ContactPhone contactPhone = contactPhoneMapper.map(newPhone);
    delivery.setContactPhone(contactPhone);
    addConditionAfterChange(delivery);
    return deliveryRepository.save(delivery);
  }

  private DeliveryType getDeliveryTypeAndCheckAvailability(UUID deliveryTypeId) {
    DeliveryType deliveryType = deliveryTypeRepository.findById(deliveryTypeId)
        .orElseThrow(() -> new EntityNotFoundException("DeliveryType", deliveryTypeId));

    if (!deliveryType.getIsAvailable()) {
      throw new DeliveryServiceBusinessException("Delivery Type is not available");
    }
    return deliveryType;
  }

  private void addConditionAfterChange(Delivery delivery) {
    DeliverySnapshot deliverySnapshot = deliveryMapper.mapToHistorySnapshot(delivery, OffsetDateTime.now());
    delivery.getDeliveryHistory().add(deliverySnapshot);
  }
}
