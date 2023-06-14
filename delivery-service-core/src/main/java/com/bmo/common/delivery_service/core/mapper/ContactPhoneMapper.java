package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryAddress;
import com.bmo.common.delivery_service.core.dbmodel.DeliverySnapshot;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import com.bmo.common.delivery_service.model.rest.DeliveryCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryResponseDto;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface DeliveryAddressMapper {

  DeliveryAddress map(DeliveryAddressDto delivery);

}
