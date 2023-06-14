package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryAddress;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryAddressMapper {

  DeliveryAddress map(DeliveryAddressDto deliveryAddressDto);

}
