package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.CatalogPersistenceEntity;

public class CatalogPersistenceAssembler {
    private CatalogPersistenceAssembler() {}

    public static CatalogPersistenceEntity toPersistenceFromDomain(Catalog catalog) {
        CatalogPersistenceEntity catalogPersistenceEntity = new CatalogPersistenceEntity();
        catalogPersistenceEntity.setId(catalog.getId());
        catalogPersistenceEntity.setProductId(catalog.getProductId());
        catalogPersistenceEntity.setCatalogName(catalog.getCatalogName());
        catalogPersistenceEntity.setCatalogState(catalog.getCatalogState());
        catalogPersistenceEntity.setDateUpdated(catalog.getDateUpdated());
        return catalogPersistenceEntity;
    }

    public static Catalog toDomainFromPersistence(CatalogPersistenceEntity catalogPersistenceEntity) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogPersistenceEntity.getId());
        catalog.setProductId(catalogPersistenceEntity.getProductId());
        catalog.setCatalogName(catalogPersistenceEntity.getCatalogName());
        catalog.setCatalogState(catalogPersistenceEntity.getCatalogState());
        catalog.setDateUpdated(catalogPersistenceEntity.getDateUpdated());
        return catalog;
    }
}
