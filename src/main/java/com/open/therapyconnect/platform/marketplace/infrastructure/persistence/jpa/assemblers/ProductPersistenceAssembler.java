package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;

public final class ProductPersistenceAssembler {
    private ProductPersistenceAssembler() {}

    public static ProductPersistenceEntity toPersistenceFromDomain(Product product) {
        ProductPersistenceEntity productPersistenceEntity = new ProductPersistenceEntity();
        productPersistenceEntity.setId(product.getId());
        productPersistenceEntity.setProductName(product.getProductName());
        productPersistenceEntity.setProductType(product.getProductType());
        productPersistenceEntity.setAvailabilityState(product.getAvailabilityState());
        productPersistenceEntity.setAvailableQuantity(product.getAvailableQuantity());
        productPersistenceEntity.setRecommendationState(product.getRecommendationState());
        productPersistenceEntity.setPriority(product.getPriority());
        productPersistenceEntity.setExpirationDate(product.getExpirationDate());
        productPersistenceEntity.setGroupType(product.getGroupType());
        productPersistenceEntity.setPrice(product.getPrice());
        return productPersistenceEntity;
    }

    public static Product toDomainFromPersistence(ProductPersistenceEntity productPersistenceEntity) {
        Product product = new Product();
        product.setId(productPersistenceEntity.getId());
        product.setProductName(productPersistenceEntity.getProductName());
        product.setProductType(productPersistenceEntity.getProductType());
        product.setAvailabilityState(productPersistenceEntity.getAvailabilityState());
        product.setAvailableQuantity(productPersistenceEntity.getAvailableQuantity());
        product.setRecommendationState(productPersistenceEntity.getRecommendationState());
        product.setPriority(productPersistenceEntity.getPriority());
        product.setExpirationDate(productPersistenceEntity.getExpirationDate());
        product.setGroupType(productPersistenceEntity.getGroupType());
        product.setPrice(productPersistenceEntity.getPrice());
        return product;
    }
}
