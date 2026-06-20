package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * JPA persistence entity for notes (base table).
 *
 * <p>Maps to the {@code notes} table. Uses TABLE_PER_CLASS inheritance strategy
 * so that {@code parent_notes} and {@code teacher_notes} are separate tables.</p>
 */
@Entity
@Table(name = "notes")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 50)
@Getter
@Setter
@NoArgsConstructor
public class NotePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private LocalDateTime noteDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConditionType conditionType;

    @Column(length = 500)
    private String conditionDescription;

    @Column(nullable = false)
    private Long authorProfileId;
}
