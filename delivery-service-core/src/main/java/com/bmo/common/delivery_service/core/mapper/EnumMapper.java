package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryStatus;
import com.bmo.common.delivery_service.model.rest.DeliveryStatusDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface EnumMapper {
  DeliveryStatus map(DeliveryStatusDto status);

}
