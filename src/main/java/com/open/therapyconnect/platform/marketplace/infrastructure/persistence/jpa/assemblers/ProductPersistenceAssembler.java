package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;

public final class ProductPersistenceAssembler {

    private ProductPersistenceAssembler() {}

    public static Product toDomainFromPersistence(ProductPersistenceEntity entity) {
        if (entity == null) return null;
        var product = new Product();
        product.setId(entity.getId());
        product.setCatalogId(entity.getCatalogId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setImageUrl(entity.getImageUrl());
        product.setRecommendedFor(entity.getRecommendedFor());
        return product;
    }

    public static ProductPersistenceEntity toPersistenceFromDomain(Product product) {
        if (product == null) return null;
        var entity = new ProductPersistenceEntity();
        if (product.getId() != null) entity.setId(product.getId());
        entity.setCatalogId(product.getCatalogId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImageUrl(product.getImageUrl());
        entity.setRecommendedFor(product.getRecommendedFor());
        return entity;
    }
}
