package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Request payload for creating a parent note.
 */
@Schema(
    name = "CreateParentNoteRequest",
    description = "Request payload for creating a new parent note"
)
public record CreateParentNoteResource(
    @Schema(description = "Date of the note", example = "2026-06-01T10:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Good progress observed at home", requiredMode = Schema.RequiredMode.REQUIRED)
    String content,

    @Schema(description = "Condition type", example = "ADHD", requiredMode = Schema.RequiredMode.REQUIRED)
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Shows difficulty concentrating")
    String conditionDescription,

    @Schema(description = "Author profile id (IAM reference)", example = "15", requiredMode = Schema.RequiredMode.REQUIRED)
    Long authorProfileId,

    @Schema(description = "Recommended next steps", example = "Practice reading for 20 minutes daily", requiredMode = Schema.RequiredMode.REQUIRED)
    String nextSteps
) {
    public CreateParentNoteResource {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("Content is required");
        if (conditionType == null) throw new IllegalArgumentException("Condition type is required");
        if (authorProfileId == null || authorProfileId < 1) throw new IllegalArgumentException("Author profile id is required");
        if (nextSteps == null || nextSteps.isBlank()) throw new IllegalArgumentException("Next steps is required");
        if (noteDate == null) noteDate = LocalDateTime.now();
        if (conditionDescription == null) conditionDescription = "";
    }
}
