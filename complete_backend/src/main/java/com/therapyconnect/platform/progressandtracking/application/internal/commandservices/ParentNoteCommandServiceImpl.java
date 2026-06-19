package com.therapyconnect.platform.progressandtracking.application.internal.commandservices;

import com.therapyconnect.platform.progressandtracking.application.commandservices.ParentNoteCommandService;
import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.CreateParentNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateParentNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.repositories.ParentNoteRepository;
import com.therapyconnect.platform.shared.application.result.ApplicationError;
import com.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

/**
 * Application service that executes parent note commands.
 */
@Service
public class ParentNoteCommandServiceImpl implements ParentNoteCommandService {

    private final ParentNoteRepository parentNoteRepository;

    public ParentNoteCommandServiceImpl(ParentNoteRepository parentNoteRepository) {
        this.parentNoteRepository = parentNoteRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateParentNoteCommand command) {
        try {
            var parentNote = new ParentNote(command);
            var saved = parentNoteRepository.save(parentNote);
            return Result.success(saved.getId());
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("ParentNote", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-parent-note", e.getMessage()));
        }
    }

    @Override
    public Result<ParentNote, ApplicationError> handle(Long noteId, UpdateParentNoteCommand command) {
        if (!parentNoteRepository.existsById(noteId)) {
            return Result.failure(ApplicationError.notFound("ParentNote", noteId.toString()));
        }
        try {
            var result = parentNoteRepository.findById(noteId);
            if (result.isEmpty()) {
                return Result.failure(ApplicationError.notFound("ParentNote", noteId.toString()));
            }
            var parentNoteToUpdate = result.get();
            var updatedNote = parentNoteRepository.save(parentNoteToUpdate.updateInformation(command));
            return Result.success(updatedNote);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("ParentNote", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-parent-note", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteNoteCommand command) {
        if (!parentNoteRepository.existsById(command.noteId())) {
            return Result.failure(ApplicationError.notFound("ParentNote", command.noteId().toString()));
        }
        try {
            parentNoteRepository.deleteById(command.noteId());
            return Result.success(command.noteId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-parent-note", e.getMessage()));
        }
    }
}
