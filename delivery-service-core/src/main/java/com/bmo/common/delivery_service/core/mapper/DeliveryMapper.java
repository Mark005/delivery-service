package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryMapper {


  DeliveryResponseDto mapToResponseDto(Delivery delivery);

  Delivery mapFromCreateDto(UUID orderId, UUID userId, DeliveryCreateDto createDto);
}
