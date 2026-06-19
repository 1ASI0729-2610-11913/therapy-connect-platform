package com.therapyconnect.platform.progressandtracking.domain.model.aggregates;

import com.therapyconnect.platform.progressandtracking.domain.model.commands.CreateTeacherNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateTeacherNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.events.NoteUpdatedEvent;
import com.therapyconnect.platform.progressandtracking.domain.model.events.TeacherNoteCreatedEvent;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionDescription;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteContent;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteDate;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ProfileId;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.SessionId;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import lombok.Getter;
import lombok.Setter;

/**
 * TeacherNote entity.
 *
 * <p>
 * Extends Note with {@code sessionId} and {@code teacherNoteType}, specific to
 * notes written by teachers. The type distinguishes between PERSONAL and INSTITUTIONAL plans.
 * </p>
 */
@Getter
public class TeacherNote extends Note {

    @Setter
    private SessionId sessionId;

    @Setter
    private TeacherNoteType teacherNoteType;

    /**
     * Default constructor.
     */
    public TeacherNote() {
        super();
        this.sessionId = new SessionId(1L);
        this.teacherNoteType = TeacherNoteType.PERSONAL;
    }

    /**
     * Constructor using a CreateTeacherNoteCommand.
     *
     * @param command the command carrying the creation data
     */
    public TeacherNote(CreateTeacherNoteCommand command) {
        this.setNoteDate(new NoteDate(command.noteDate()));
        this.setContent(new NoteContent(command.content()));
        this.setConditionType(command.conditionType());
        this.setConditionDescription(new ConditionDescription(command.conditionDescription()));
        this.setAuthorProfileId(new ProfileId(command.authorProfileId()));
        this.sessionId = new SessionId(command.sessionId());
        this.teacherNoteType = command.teacherNoteType();
        this.registerDomainEvent(new TeacherNoteCreatedEvent(this));
    }

    /**
     * Updates this teacher note with the data from an UpdateTeacherNoteCommand.
     *
     * @param command the command carrying the updated data
     * @return this TeacherNote instance (fluent)
     */
    public TeacherNote updateInformation(UpdateTeacherNoteCommand command) {
        this.setNoteDate(new NoteDate(command.noteDate()));
        this.setContent(new NoteContent(command.content()));
        this.setConditionType(command.conditionType());
        this.setConditionDescription(new ConditionDescription(command.conditionDescription()));
        this.sessionId = new SessionId(command.sessionId());
        this.teacherNoteType = command.teacherNoteType();
        this.registerDomainEvent(new NoteUpdatedEvent(this));
        return this;
    }
}
