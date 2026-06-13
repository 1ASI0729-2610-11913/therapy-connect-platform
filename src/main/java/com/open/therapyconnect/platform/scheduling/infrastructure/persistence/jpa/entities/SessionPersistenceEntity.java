package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.SessionStatus;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for sessions.
 */
@Entity
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
public class SessionPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String sessionDate;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private String sessionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus sessionStatus;

    private Long teacherId;

    private Long studentId;
}