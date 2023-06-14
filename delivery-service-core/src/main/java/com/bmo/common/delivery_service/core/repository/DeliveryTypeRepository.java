package com.bmo.common.delivery_service.core.repository;

import com.bmo.common.delivery_service.core.dbmodel.DeliveryType;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, UUID> {

}