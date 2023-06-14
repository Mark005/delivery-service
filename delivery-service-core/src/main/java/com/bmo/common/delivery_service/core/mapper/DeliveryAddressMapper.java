package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.dbmodel.DeliverySnapshot;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryMapper {

  DeliveryResponseDto mapToResponseDto(Delivery delivery);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "deliveryHistory", ignore = true)
  Delivery mapFromCreateDto(UUID orderId, UUID userId, DeliveryType deliveryType, DeliveryCreateDto createDto);

  DeliverySnapshot mapToHistorySnapshot(Delivery delivery, OffsetDateTime updateDateTime);


}
