package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.CatalogStates;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.ProductId;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.converters.ProductIdPersistenceConverter;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalogs")
@Getter
@Setter
public class CatalogPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "catalog_state", nullable = false)
    private CatalogStates catalogState;

    @Column(name = "date_updated", nullable = false)
    private String dateUpdated;

    @Convert(converter = ProductIdPersistenceConverter.class)
    @Column(name = "product_id", nullable = false)
    private ProductId productId;
}
