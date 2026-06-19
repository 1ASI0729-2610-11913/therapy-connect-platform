package com.open.therapyconnect.platform.deliverytracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources.DeliveryDetailResource;

public final class DeliveryDetailResourceFromEntityAssembler {

    private DeliveryDetailResourceFromEntityAssembler() {}

    public static DeliveryDetailResource toResourceFromEntity(Delivery delivery) {
        return new DeliveryDetailResource(
                delivery.getId(),
                delivery.getStatus().name(),
                delivery.getScheduledTime(),
                new DeliveryDetailResource.ResponsibleResource(delivery.getResponsibleName()),
                new DeliveryDetailResource.VehicleResource(
                        delivery.getVehiclePlate(),
                        delivery.getVehicleType(),
                        delivery.getVehicleBrand()
                )
        );
    }
}
