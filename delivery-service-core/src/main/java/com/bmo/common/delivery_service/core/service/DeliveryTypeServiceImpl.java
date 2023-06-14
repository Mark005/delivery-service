package com.bmo.common.delivery_service.core.service;

import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import com.bmo.common.delivery_service.core.mapper.DeliveryTypeMapper;
import com.bmo.common.delivery_service.core.mapper.PageableMapper;
import com.bmo.common.delivery_service.core.repository.DeliveryTypeRepository;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeCreateDto;
import com.bmo.common.delivery_service.model.rest.DeliveryTypeUpdateDto;
import com.bmo.common.delivery_service.model.rest.PageRequestDto;
import com.bmo.common.delivery_service.model.rest.exception.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

  private final DeliveryTypeRepository deliveryTypeRepository;

  private final DeliveryTypeMapper deliveryTypeMapper;

  @Override
  public DeliveryType createDeliveryType(DeliveryTypeCreateDto createDto) {
    DeliveryType newDeliveryType = deliveryTypeMapper.mapToEntity(createDto);
    return deliveryTypeRepository.save(newDeliveryType);
  }

  @Override
  public DeliveryType getDeliveryTypeById(UUID deliveryTypeId) {
    return deliveryTypeRepository.findById(deliveryTypeId)
        .orElseThrow(() ->
            new EntityNotFoundException("DeliveryType", deliveryTypeId));
  }

  @Override
  public Page<DeliveryType> getDeliveryTypes(PageRequestDto pageRequestDto) {
    return deliveryTypeRepository.findAll(PageableMapper.map(pageRequestDto));
  }

  @Override
  public DeliveryType updateDeliveryType(UUID deliveryTypeId, DeliveryTypeUpdateDto updateDto) {
    DeliveryType deliveryTypeFromDb = getDeliveryTypeById(deliveryTypeId);

    DeliveryType merged = deliveryTypeMapper.merge(deliveryTypeFromDb, updateDto);

    return deliveryTypeRepository.save(merged);
  }

  @Override
  public void updateEnableDeliveryType(UUID deliveryTypeId, boolean isAvailable) {
    DeliveryType deliveryTypeFromDb = getDeliveryTypeById(deliveryTypeId);
    deliveryTypeFromDb.setIsAvailable(isAvailable);
    deliveryTypeRepository.save(deliveryTypeFromDb);
  }
}
