package com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertStatus;
import com.open.therapyconnect.platform.alertmonitoring.domain.repositories.AlertRepository;
import com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.assemblers.AlertPersistenceAssembler;
import com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.repositories.AlertPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlertRepositoryImpl implements AlertRepository {

    private final AlertPersistenceRepository persistenceRepository;

    public AlertRepositoryImpl(AlertPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<Alert> findByStatus(String status) {
        return persistenceRepository.findByStatus(AlertStatus.valueOf(status.toUpperCase()))
                .stream()
                .map(AlertPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
}
