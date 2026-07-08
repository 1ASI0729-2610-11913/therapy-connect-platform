package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.ProgressStates;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dependents")
@Getter
@Setter
public class DependentPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(name = "dependent_name", nullable = false)
    private String dependentName;

    @Column(name = "dependent_condition", nullable = false)
    private String dependentCondition;

    @Column(name = "need_level", nullable = false)
    private String needLevel;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "progress_state", nullable = false)
    private ProgressStates progressState;
}
