package com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DeliveryDetail", description = "Full detail of a delivery")
public record DeliveryDetailResource(
        @Schema(description = "Delivery ID", example = "1")
        Long id,

        @Schema(description = "Delivery status", example = "PENDING")
        String status,

        @Schema(description = "Scheduled delivery time", example = "14:00")
        String scheduledTime,

        @Schema(description = "Responsible person information")
        ResponsibleResource responsible,

        @Schema(description = "Vehicle information")
        VehicleResource vehicle
) {
    @Schema(name = "Responsible", description = "Responsible person information")
    public record ResponsibleResource(
            @Schema(description = "Full name of the responsible person", example = "John Doe")
            String name
    ) {}

    @Schema(name = "Vehicle", description = "Vehicle information")
    public record VehicleResource(
            @Schema(description = "Vehicle plate", example = "ABC-123")
            String plate,

            @Schema(description = "Vehicle type", example = "Truck")
            String type,

            @Schema(description = "Vehicle brand", example = "Ford")
            String brand
    ) {}
}
