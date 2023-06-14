package com.bmo.common.delivery_service.core.controller;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.mapper.DeliveryMapper;
import com.bmo.common.delivery_service.core.service.DeliveryService;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import com.bmo.common.gateway.header.GatewayHeader;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/users/current/orders/{id}/delivery")
  public ResponseEntity<DeliveryResponseDto> getDeliveryForCurrentUserByOrderId(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @NotNull @PathVariable("id") UUID orderId) {

    Delivery delivery = deliveryService.getDeliveryForCurrentUserByOrderId(orderId, userId);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/users/{userId}/orders/{orderId}/delivery")
  public ResponseEntity<DeliveryResponseDto> createDelivery(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryCreateDto createDto) {

    Delivery delivery = deliveryService.createDelivery(orderId, userId, createDto);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }

/*
  @PatchMapping("/users/{userId}/orders/{orderId}/delivery")
  public ResponseEntity<DeliveryResponseDto> updateDelivery(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody @Valid DeliveryCreateDto createDto) {

    Delivery delivery = deliveryService.createDelivery(orderId, userId, createDto);
    DeliveryResponseDto responseDto = deliveryMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }
  @DeleteMapping("/users/current/addresses/{id}")
  public ResponseEntity<Void> deleteAddress(
      @NotNull @RequestHeader(GatewayHeader.USER_ID) UUID userId,
      @NotNull @PathVariable("id") UUID addressId) {

    addressService.deleteUsersAddress(userId, addressId);
    return ResponseEntity.noContent().build();
  }
*/
}
