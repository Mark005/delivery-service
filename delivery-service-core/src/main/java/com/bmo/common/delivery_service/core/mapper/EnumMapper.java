package com.bmo.common.delivery_service.core.mapper;

import com.bmo.common.delivery_service.core.configs.MapStructCommonConfig;
import com.bmo.common.delivery_service.core.dbmodel.ContactPhone;
import com.bmo.common.delivery_service.core.dbmodel.DeliveryAddress;
import com.bmo.common.delivery_service.model.rest.ContactPhoneDto;
import com.bmo.common.delivery_service.model.rest.DeliveryAddressDto;
import org.mapstruct.Mapper;

@Mapper(config = MapStructCommonConfig.class)
public interface ContactPhoneMapper {

  ContactPhone map(ContactPhoneDto contactPhoneDto);

}
