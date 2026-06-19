package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.TeacherNoteResource;

/**
 * Assembler to convert a TeacherNote entity to a TeacherNoteResource.
 */
public class TeacherNoteResourceFromEntityAssembler {

    /**
     * Converts a TeacherNote entity to a TeacherNoteResource.
     *
     * @param entity The {@link TeacherNote} entity to convert.
     * @return The {@link TeacherNoteResource} resource that results from the conversion.
     */
    public static TeacherNoteResource toResourceFromEntity(TeacherNote entity) {
        return new TeacherNoteResource(
                entity.getId(),
                entity.getNoteDate().value(),
                entity.getContent().value(),
                entity.getConditionType(),
                entity.getConditionDescription().value(),
                entity.getAuthorProfileId().id(),
                entity.getSessionId().id(),
                entity.getTeacherNoteType()
        );
    }
}
