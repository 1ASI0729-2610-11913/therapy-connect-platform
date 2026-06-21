package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {

    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(
                product.getId(),
                product.getCatalogId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getRecommendedFor()
        );
    }
}
