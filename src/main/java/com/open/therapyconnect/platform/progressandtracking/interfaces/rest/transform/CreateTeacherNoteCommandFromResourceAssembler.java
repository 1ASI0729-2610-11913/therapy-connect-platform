package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.CreateTeacherNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.CreateTeacherNoteResource;

/**
 * Assembler to convert a CreateTeacherNoteResource to a CreateTeacherNoteCommand.
 */
public class CreateTeacherNoteCommandFromResourceAssembler {

    /**
     * Converts a CreateTeacherNoteResource to a CreateTeacherNoteCommand.
     *
     * @param resource The {@link CreateTeacherNoteResource} resource to convert.
     * @return The {@link CreateTeacherNoteCommand} command that results from the conversion.
     */
    public static CreateTeacherNoteCommand toCommandFromResource(CreateTeacherNoteResource resource) {
        return new CreateTeacherNoteCommand(
                resource.noteDate(),
                resource.content(),
                resource.conditionType(),
                resource.conditionDescription(),
                resource.authorProfileId(),
                resource.sessionId(),
                resource.teacherNoteType()
        );
    }
}
