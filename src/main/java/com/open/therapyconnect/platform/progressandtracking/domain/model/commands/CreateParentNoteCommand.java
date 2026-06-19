package com.open.therapyconnect.platform.progressandtracking.domain.model.commands;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;

import java.time.LocalDateTime;

/**
 * Command for creating a parent note.
 *
 * @param noteDate             the date of the note
 * @param content              the note content
 * @param conditionType        the type of condition
 * @param conditionDescription optional description of the condition
 * @param authorProfileId      the profile id of the parent author (from IAM)
 * @param nextSteps            the recommended next steps
 */
public record CreateParentNoteCommand(
        LocalDateTime noteDate,
        String content,
        ConditionType conditionType,
        String conditionDescription,
        Long authorProfileId,
        String nextSteps) {
}
