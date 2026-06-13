package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.ReminderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for reminders.
 */
@Entity
@Table(name = "reminders")
@Getter
@Setter
@NoArgsConstructor
public class ReminderPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String reminderDate;

    @Column(nullable = false)
    private String reminderTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReminderStatus reminderStatus;

    private Long sessionId;
}