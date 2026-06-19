package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Request payload for creating a teacher note.
 */
@Schema(
    name = "CreateTeacherNoteRequest",
    description = "Request payload for creating a new teacher note"
)
public record CreateTeacherNoteResource(
    @Schema(description = "Date of the note", example = "2026-06-02T09:30:00", requiredMode = Schema.RequiredMode.REQUIRED)
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Student participated actively in session", requiredMode = Schema.RequiredMode.REQUIRED)
    String content,

    @Schema(description = "Condition type", example = "ASPERGER", requiredMode = Schema.RequiredMode.REQUIRED)
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Improved social interaction")
    String conditionDescription,

    @Schema(description = "Author profile id (IAM reference)", example = "28", requiredMode = Schema.RequiredMode.REQUIRED)
    Long authorProfileId,

    @Schema(description = "Session id (Reservations BC reference)", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    Long sessionId,

    @Schema(description = "Teacher note type (PERSONAL or INSTITUTIONAL)", example = "PERSONAL", requiredMode = Schema.RequiredMode.REQUIRED)
    TeacherNoteType teacherNoteType
) {
    public CreateTeacherNoteResource {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("Content is required");
        if (conditionType == null) throw new IllegalArgumentException("Condition type is required");
        if (authorProfileId == null || authorProfileId < 1) throw new IllegalArgumentException("Author profile id is required");
        if (sessionId == null || sessionId < 1) throw new IllegalArgumentException("Session id is required");
        if (teacherNoteType == null) throw new IllegalArgumentException("Teacher note type is required");
        if (noteDate == null) noteDate = LocalDateTime.now();
        if (conditionDescription == null) conditionDescription = "";
    }
}
