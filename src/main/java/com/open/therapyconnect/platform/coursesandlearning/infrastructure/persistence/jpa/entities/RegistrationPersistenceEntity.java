package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.valueobjects.RegistrationStatus;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
public class RegistrationPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Long studentId;

    private String registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;

    private Integer progress;
}
