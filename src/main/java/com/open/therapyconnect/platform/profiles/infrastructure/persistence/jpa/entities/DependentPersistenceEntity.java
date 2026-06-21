package com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.profiles.domain.model.valueobjects.DependentCondition;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dependents")
@Getter
@Setter
@NoArgsConstructor
public class DependentPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private DependentCondition condition;

    @Column(nullable = false)
    private Long parentId;
}
