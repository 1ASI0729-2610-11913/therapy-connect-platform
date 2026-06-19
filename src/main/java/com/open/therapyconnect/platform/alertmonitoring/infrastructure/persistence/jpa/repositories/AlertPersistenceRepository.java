package com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertStatus;
import com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.entities.AlertPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertPersistenceRepository extends JpaRepository<AlertPersistenceEntity, Long> {
    List<AlertPersistenceEntity> findByStatus(AlertStatus status);
}
