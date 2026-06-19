package com.open.therapyconnect.platform.alertmonitoring.interfaces.rest.transform;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;
import com.open.therapyconnect.platform.alertmonitoring.interfaces.rest.resources.AlertResource;

public final class AlertResourceFromEntityAssembler {

    private AlertResourceFromEntityAssembler() {}

    public static AlertResource toResourceFromEntity(Alert alert) {
        return new AlertResource(
                alert.getId(),
                alert.getZone(),
                alert.getPpmLevel(),
                alert.getCriticality().name(),
                alert.getStatus().name(),
                alert.getDetectedAt()
        );
    }
}
