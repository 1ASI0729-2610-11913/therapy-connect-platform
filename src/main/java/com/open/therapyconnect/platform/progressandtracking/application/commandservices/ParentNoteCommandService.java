package com.open.therapyconnect.platform.progressandtracking.application.commandservices;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.CreateParentNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateParentNoteCommand;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

/**
 * Application service contract for commands over the {@link ParentNote} aggregate.
 */
public interface ParentNoteCommandService {

    /**
     * Handles parent note creation.
     *
     * @param command command containing initial parent note data
     * @return created parent note identifier or an application error
     * @see CreateParentNoteCommand
     */
    Result<Long, ApplicationError> handle(CreateParentNoteCommand command);

    /**
     * Handles parent note update.
     *
     * @param noteId  the id of the note to update
     * @param command command containing updated parent note data
     * @return updated parent note aggregate or an application error
     * @see UpdateParentNoteCommand
     */
    Result<ParentNote, ApplicationError> handle(Long noteId, UpdateParentNoteCommand command);

    /**
     * Handles parent note deletion.
     *
     * @param command command containing target note id
     * @return deleted note identifier or an application error
     * @see DeleteNoteCommand
     */
    Result<Long, ApplicationError> handle(DeleteNoteCommand command);
}
