package com.bmo.common.delivery_service.client;

import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import com.bmo.common.delivery_service.model.rest.DeliveryStatusUpdateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "delivery-service-client", url = "${feign.client.config.delivery-service.url}")
public interface DeliveryServiceClient {

  @GetMapping("/delivery-types/{id}")
  DeliveryTypeResponseDto getDeliveryTypeById(@PathVariable("id") UUID deliveryTypeId);

  @GetMapping("/users/{userId}/orders/{orderId}/delivery")
  DeliveryResponseDto getDeliveryByUserIsAndOrderId(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId);

  @PostMapping("/users/{userId}/orders/{orderId}/delivery")
  DeliveryResponseDto createDelivery(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody DeliveryCreateDto createDto);

  @PatchMapping("/users/{userId}/orders/{orderId}/delivery/status")
  DeliveryResponseDto updateDeliveryStatus(
      @PathVariable("userId") UUID userId,
      @PathVariable("orderId") UUID orderId,
      @RequestBody DeliveryStatusUpdateDto statusUpdateDto);

}
