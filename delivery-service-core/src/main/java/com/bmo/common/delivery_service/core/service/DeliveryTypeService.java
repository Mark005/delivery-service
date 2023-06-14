package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeUpdateDto;
import com.bmo.common.delivery_service.model.rest.PageRequestDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface DeliveryTypeService {

  DeliveryType createDeliveryType(DeliveryTypeCreateDto createDto);

  DeliveryType getDeliveryTypeById(UUID deliveryTypeId);

  Page<DeliveryType> getDeliveryTypes(PageRequestDto pageRequestDto);

  DeliveryType updateDeliveryType(UUID deliveryTypeId, DeliveryTypeUpdateDto updateDto);

  void updateEnableDeliveryType(UUID deliveryTypeId, boolean isAvailable);

}
