package com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;
import com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.entities.AlertPersistenceEntity;

public final class AlertPersistenceAssembler {

    private AlertPersistenceAssembler() {}

    public static Alert toDomainFromPersistence(AlertPersistenceEntity entity) {
        if (entity == null) return null;
        var alert = new Alert();
        alert.setId(entity.getId());
        alert.setZone(entity.getZone());
        alert.setPpmLevel(entity.getPpmLevel());
        alert.setCriticality(entity.getCriticality());
        alert.setStatus(entity.getStatus());
        alert.setDetectedAt(entity.getDetectedAt());
        return alert;
    }

    public static AlertPersistenceEntity toPersistenceFromDomain(Alert alert) {
        if (alert == null) return null;
        var entity = new AlertPersistenceEntity();
        if (alert.getId() != null) entity.setId(alert.getId());
        entity.setZone(alert.getZone());
        entity.setPpmLevel(alert.getPpmLevel());
        entity.setCriticality(alert.getCriticality());
        entity.setStatus(alert.getStatus());
        entity.setDetectedAt(alert.getDetectedAt());
        return entity;
    }
}
