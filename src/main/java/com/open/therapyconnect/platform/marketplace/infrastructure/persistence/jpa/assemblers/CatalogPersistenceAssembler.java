package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.CatalogPersistenceEntity;

public final class CatalogPersistenceAssembler {

    private CatalogPersistenceAssembler() {}

    public static Catalog toDomainFromPersistence(CatalogPersistenceEntity entity) {
        if (entity == null) return null;
        var catalog = new Catalog();
        catalog.setId(entity.getId());
        catalog.setName(entity.getName());
        catalog.setDescription(entity.getDescription());
        return catalog;
    }

    public static CatalogPersistenceEntity toPersistenceFromDomain(Catalog catalog) {
        if (catalog == null) return null;
        var entity = new CatalogPersistenceEntity();
        if (catalog.getId() != null) entity.setId(catalog.getId());
        entity.setName(catalog.getName());
        entity.setDescription(catalog.getDescription());
        return entity;
    }
}
