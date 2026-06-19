package com.open.therapyconnect.platform.progressandtracking.domain.model.commands;

/**
 * Command for deleting a note by its id.
 *
 * @param noteId the id of the note to delete
 */
public record DeleteNoteCommand(Long noteId) {
}
