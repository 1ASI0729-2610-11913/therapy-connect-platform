package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.CreateParentNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.CreateParentNoteResource;

/**
 * Assembler to convert a CreateParentNoteResource to a CreateParentNoteCommand.
 */
public class CreateParentNoteCommandFromResourceAssembler {

    /**
     * Converts a CreateParentNoteResource to a CreateParentNoteCommand.
     *
     * @param resource The {@link CreateParentNoteResource} resource to convert.
     * @return The {@link CreateParentNoteCommand} command that results from the conversion.
     */
    public static CreateParentNoteCommand toCommandFromResource(CreateParentNoteResource resource) {
        return new CreateParentNoteCommand(
                resource.noteDate(),
                resource.content(),
                resource.conditionType(),
                resource.conditionDescription(),
                resource.authorProfileId(),
                resource.nextSteps()
        );
    }
}
