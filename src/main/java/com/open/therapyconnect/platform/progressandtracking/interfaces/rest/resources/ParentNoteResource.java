package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Parent note response resource.
 */
@Schema(
    name = "ParentNoteResponse",
    description = "Parent note information response"
)
public record ParentNoteResource(
    @Schema(description = "Note unique identifier", example = "1")
    Long id,

    @Schema(description = "Date of the note", example = "2026-06-01T10:00:00")
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Good progress observed at home")
    String content,

    @Schema(description = "Condition type", example = "ADHD")
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Shows difficulty concentrating")
    String conditionDescription,

    @Schema(description = "Author profile id (IAM reference)", example = "15")
    Long authorProfileId,

    @Schema(description = "Recommended next steps", example = "Practice reading for 20 minutes daily")
    String nextSteps
) {
}
