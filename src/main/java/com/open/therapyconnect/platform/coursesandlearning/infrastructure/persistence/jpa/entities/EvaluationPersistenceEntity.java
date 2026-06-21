package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evaluations")
@Getter
@Setter
@NoArgsConstructor
public class EvaluationPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private Long studentId;

    private Integer score;

    private String feedback;

    private String evaluationDate;
}
