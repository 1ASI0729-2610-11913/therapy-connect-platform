package com.therapyconnect.platform.progressandtracking.domain.model.events;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.Note;

/**
 * Domain event raised when a note is created.
 *
 * @param note the newly created note
 */
public record NoteCreatedEvent(Note note) {
}
