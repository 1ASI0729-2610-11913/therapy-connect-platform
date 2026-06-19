package com.open.therapyconnect.platform.progressandtracking.domain.model.events;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;

/**
 * Domain event raised when a parent note is created.
 *
 * @param parentNote the newly created parent note
 */
public record ParentNoteCreatedEvent(ParentNote parentNote) {
}
