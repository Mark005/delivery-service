package com.bmo.common.delivery_service.core.repository;

import com.bmo.common.delivery_service.core.dbmodel.Delivery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

  Optional<Delivery> findByOrderIdAndUserId(UUID orderId, UUID userId);
}