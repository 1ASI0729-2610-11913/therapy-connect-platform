package com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DeliverySummary", description = "Summary of a delivery for the logistics panel")
public record DeliverySummaryResource(
        @Schema(description = "Delivery ID", example = "1")
        Long id,

        @Schema(description = "Delivery status", example = "PENDING")
        String status,

        @Schema(description = "Responsible person ID", example = "10")
        Long responsibleId,

        @Schema(description = "Vehicle plate", example = "ABC-123")
        String vehiclePlate,

        @Schema(description = "Number of items to deliver", example = "5")
        Integer itemCount,

        @Schema(description = "Scheduled delivery time", example = "14:00")
        String scheduledTime
) {}
