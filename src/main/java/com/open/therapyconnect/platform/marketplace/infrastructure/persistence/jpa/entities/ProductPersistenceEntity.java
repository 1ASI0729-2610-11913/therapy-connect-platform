package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long catalogId;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private String recommendedFor;
}
