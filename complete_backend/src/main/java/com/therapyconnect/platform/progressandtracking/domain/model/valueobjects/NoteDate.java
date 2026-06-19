package com.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

import java.time.LocalDateTime;

/**
 * Value object representing the date of a note.
 *
 * @param value The note date. It cannot be null.
 */
public record NoteDate(LocalDateTime value) {
    public NoteDate {
        if (value == null) {
            throw new IllegalArgumentException("Note date cannot be null");
        }
    }
}
