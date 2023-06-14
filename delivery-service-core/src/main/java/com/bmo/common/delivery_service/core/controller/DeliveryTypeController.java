package com.bmo.common.delivery_service.core.controller;

import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.core.mapper.DeliveryTypeMapper;
import com.bmo.common.delivery_service.core.service.DeliveryTypeService;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeResponseDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeUpdateDto;
import com.bmo.common.delivery_service.model.rest.PageRequestDto;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryTypeController {

  private final DeliveryTypeService deliveryTypeService;
  private final DeliveryTypeMapper deliveryTypeMapper;

  @GetMapping("/delivery-types/{id}")
  public ResponseEntity<DeliveryTypeResponseDto> getDeliveryTypeById(
      @NotNull @PathVariable("id") UUID deliveryTypeId) {

    DeliveryType deliveryType = deliveryTypeService.getDeliveryTypeById(deliveryTypeId);
    DeliveryTypeResponseDto responseDto = deliveryTypeMapper.mapToResponseDto(deliveryType);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/delivery-types")
  public ResponseEntity<Page<DeliveryTypeResponseDto>> getDeliveryTypes(
      PageRequestDto pageRequestDto) {

    Page<DeliveryType> page = deliveryTypeService.getDeliveryTypes(pageRequestDto);
    Page<DeliveryTypeResponseDto> responseDto = page.map(deliveryTypeMapper::mapToResponseDto);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/delivery-types")
  public ResponseEntity<DeliveryTypeResponseDto> createDeliveryType(
      @RequestBody @Valid DeliveryTypeCreateDto createDto) {

    DeliveryType deliveryType = deliveryTypeService.createDeliveryType(createDto);
    DeliveryTypeResponseDto responseDto = deliveryTypeMapper.mapToResponseDto(deliveryType);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/delivery-types/{id}")
  public ResponseEntity<DeliveryTypeResponseDto> updateDeliveryType(
      @NotNull @PathVariable("id") UUID deliveryTypeId,
      @RequestBody @Valid DeliveryTypeUpdateDto updateDto) {

    DeliveryType delivery = deliveryTypeService.updateDeliveryType(deliveryTypeId, updateDto);
    DeliveryTypeResponseDto responseDto = deliveryTypeMapper.mapToResponseDto(delivery);
    return ResponseEntity.ok(responseDto);
  }
  @PatchMapping("/delivery-types/{id}/enable")
  public ResponseEntity<Void> enableDeliveryType(
      @NotNull @PathVariable("id") UUID deliveryTypeId) {

    deliveryTypeService.updateEnableDeliveryType(deliveryTypeId, true);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/delivery-types/{id}/disable")
  public ResponseEntity<Void> disableDeliveryType(
      @NotNull @PathVariable("id") UUID deliveryTypeId) {

    deliveryTypeService.updateEnableDeliveryType(deliveryTypeId, false);
    return ResponseEntity.noContent().build();
  }

}
