package com.open.therapyconnect.platform.deliverytracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources.DeliverySummaryResource;

public final class DeliverySummaryResourceFromEntityAssembler {

    private DeliverySummaryResourceFromEntityAssembler() {}

    public static DeliverySummaryResource toResourceFromEntity(Delivery delivery) {
        return new DeliverySummaryResource(
                delivery.getId(),
                delivery.getStatus().name(),
                delivery.getResponsibleId(),
                delivery.getVehiclePlate(),
                delivery.getItemCount(),
                delivery.getScheduledTime()
        );
    }
}
