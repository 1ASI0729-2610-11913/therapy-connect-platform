package com.open.therapyconnect.platform.progressandtracking.domain.model.commands;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;

import java.time.LocalDateTime;

/**
 * Command for creating a teacher note.
 *
 * @param noteDate             the date of the note
 * @param content              the note content
 * @param conditionType        the type of condition
 * @param conditionDescription optional description of the condition
 * @param authorProfileId      the profile id of the teacher author (from IAM)
 * @param sessionId            the session id (from Reservations BC)
 * @param teacherNoteType      PERSONAL or INSTITUTIONAL
 */
public record CreateTeacherNoteCommand(
        LocalDateTime noteDate,
        String content,
        ConditionType conditionType,
        String conditionDescription,
        Long authorProfileId,
        Long sessionId,
        TeacherNoteType teacherNoteType) {
}
