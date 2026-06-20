package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for teacher notes.
 *
 * <p>Extends {@link NotePersistenceEntity} with {@code sessionId} and {@code teacherNoteType}.
 * Maps to the {@code teacher_notes} table via JOINED inheritance.</p>
 */
@Entity
@Table(name = "teacher_notes")
@PrimaryKeyJoinColumn(name = "note_id")
@DiscriminatorValue("TEACHER_NOTE")
@Getter
@Setter
@NoArgsConstructor
public class TeacherNotePersistenceEntity extends NotePersistenceEntity {

    @Column(nullable = false)
    private Long sessionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TeacherNoteType teacherNoteType;
}
