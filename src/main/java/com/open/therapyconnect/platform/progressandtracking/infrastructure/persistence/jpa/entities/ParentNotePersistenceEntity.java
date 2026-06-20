package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for parent notes.
 *
 * <p>Extends {@link NotePersistenceEntity} with {@code nextSteps}.
 * Maps to the {@code parent_notes} table via JOINED inheritance.</p>
 */
@Entity
@Table(name = "parent_notes")
@PrimaryKeyJoinColumn(name = "note_id")
@DiscriminatorValue("PARENT_NOTE")
@Getter
@Setter
@NoArgsConstructor
public class ParentNotePersistenceEntity extends NotePersistenceEntity {

    @Column(nullable = false, length = 1000)
    private String nextSteps;
}
