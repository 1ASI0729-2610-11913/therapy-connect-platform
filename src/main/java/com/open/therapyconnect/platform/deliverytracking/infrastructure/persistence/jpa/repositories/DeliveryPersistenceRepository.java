package com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.deliverytracking.domain.model.valueobjects.DeliveryStatus;
import com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.entities.DeliveryPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPersistenceRepository extends JpaRepository<DeliveryPersistenceEntity, Long> {
    List<DeliveryPersistenceEntity> findByDeliveryDateAndStatus(String deliveryDate, DeliveryStatus status);
}
