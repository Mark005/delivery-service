package com.bmo.common.delivery_service.core.controller;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.mapper.DeliveryMapper;
import com.bmo.common.delivery_service.core.service.DeliveryService;
import com.bmo.common.delivery_service.model.rest.ContactPhoneDto;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import com.bmo.common.delivery_service.model.rest.DeliveryStatusUpdateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypePatchDto;
import com.bmo.common.gateway.header.GatewayHeader;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

  private final DeliveryService deliveryService;

  private final DeliveryMapper deliveryMapper;

  @PostMapping("/users/{userId}/orders/{orderId}/delivery")
  public ResponseEntity<DeliveryResponseDto> createDelivery(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryCreateDto createDto) {

    Delivery delivery = deliveryService.createDelivery(orderId, userId, createDto);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/users/{userId}/orders/{orderId}/delivery")
  public ResponseEntity<DeliveryResponseDto> getDeliveryByUserIsAndOrderId(
      @NotNull @PathVariable("userId") UUID userId,
      @NotNull @PathVariable("orderId") UUID orderId) {

    Delivery delivery = deliveryService.getDeliveryByUserIsAndOrderId(orderId, userId);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/users/current/orders/{id}/delivery")
  public ResponseEntity<DeliveryResponseDto> getDeliveryForCurrentUserByOrderId(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @NotNull @PathVariable("id") UUID orderId) {

    Delivery delivery = deliveryService.getDeliveryByUserIsAndOrderId(orderId, userId);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }


  @PatchMapping("/users/{userId}/orders/{orderId}/delivery/status")
  public ResponseEntity<DeliveryResponseDto> updateDeliveryStatus(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryStatusUpdateDto statusUpdateDto) {

    Delivery delivery = deliveryService.updateDeliveryStatus(orderId, userId, statusUpdateDto.getStatus());
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/users/current/orders/{orderId}/delivery/delivery-type")
  public ResponseEntity<DeliveryResponseDto> updateDeliveryType(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryTypePatchDto patchDto) {

    Delivery delivery = deliveryService.updateDeliveryType(orderId, userId, patchDto.getDeliveryTypeId());
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/users/current/orders/{orderId}/delivery/delivery-address")
  public ResponseEntity<DeliveryResponseDto> updateDeliveryAddress(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryAddressDto newAddress) {

    Delivery delivery = deliveryService.updateDeliveryAddress(orderId, userId, newAddress);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/users/current/orders/{orderId}/delivery/contact-phone")
  public ResponseEntity<DeliveryResponseDto> updateContactPhone(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid ContactPhoneDto newPhone) {

    Delivery delivery = deliveryService.updateContactPhone(orderId, userId, newPhone);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

}
