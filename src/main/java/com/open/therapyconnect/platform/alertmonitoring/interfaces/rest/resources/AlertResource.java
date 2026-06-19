package com.open.therapyconnect.platform.alertmonitoring.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Alert", description = "Alert information for the operations panel")
public record AlertResource(
        @Schema(description = "Alert ID", example = "1")
        Long id,

        @Schema(description = "Zone where the alert was detected", example = "Zone A")
        String zone,

        @Schema(description = "PPM level measured at detection", example = "450.5")
        Double ppmLevel,

        @Schema(description = "Alert criticality level", example = "HIGH")
        String criticality,

        @Schema(description = "Alert status", example = "PENDING")
        String status,

        @Schema(description = "Date and time the alert was detected", example = "2026-06-18T10:30:00")
        String detectedAt
) {}
