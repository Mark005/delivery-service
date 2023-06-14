package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryTypeSnapshot;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeResponseDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryTypeMapper {

  DeliveryTypeResponseDto mapToResponseDto(DeliveryType deliveryType);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deliveries", ignore = true)
  DeliveryType mapToEntity(DeliveryTypeCreateDto createDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deliveries", ignore = true)
  DeliveryType merge(@MappingTarget DeliveryType deliveryTypeFromDb, DeliveryTypeUpdateDto updateDto);

  DeliveryTypeSnapshot mapToSnapshot(DeliveryType deliveryType);
}
