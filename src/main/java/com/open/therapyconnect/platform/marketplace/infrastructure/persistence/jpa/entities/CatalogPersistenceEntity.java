package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalogs")
@Getter
@Setter
@NoArgsConstructor
public class CatalogPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String name;

    private String description;
}
