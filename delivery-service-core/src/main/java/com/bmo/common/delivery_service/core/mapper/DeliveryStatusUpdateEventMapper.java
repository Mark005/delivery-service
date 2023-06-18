package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.model.kafka.DeliveryStatusUpdateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryStatusUpdateEventMapper {

  @Mapping(target = "deliveryId", source = "id")
  DeliveryStatusUpdateEvent mapToEvent(Delivery contactPhoneDto);

}
