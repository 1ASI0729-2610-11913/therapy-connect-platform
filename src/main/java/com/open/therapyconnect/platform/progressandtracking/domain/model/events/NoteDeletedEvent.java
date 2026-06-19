package com.open.therapyconnect.platform.progressandtracking.domain.model.events;

/**
 * Domain event raised when a note is deleted.
 *
 * @param noteId the id of the deleted note
 */
public record NoteDeletedEvent(Long noteId) {
}
