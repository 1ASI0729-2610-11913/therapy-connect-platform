package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.ReminderStatus;

/**
 * Reminder response resource.
 */
@Schema(name = "ReminderResponse", description = "Reminder information response")
public record ReminderResource(
        @Schema(description = "Reminder unique identifier", example = "1")
        Long id,

        @Schema(description = "Reminder title", example = "Recordatorio padres - Mateo")
        String title,

        @Schema(description = "Reminder date", example = "2026-05-19")
        String reminderDate,

        @Schema(description = "Reminder time", example = "18:00")
        String reminderTime,

        @Schema(description = "Reminder status", example = "pending")
        ReminderStatus reminderStatus,

        @Schema(description = "Associated session ID", example = "1")
        Long sessionId
) {}