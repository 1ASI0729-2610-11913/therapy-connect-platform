package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.converters;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.ProductId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductIdPersistenceConverter implements AttributeConverter<ProductId, Long> {
    @Override
    public Long convertToDatabaseColumn(ProductId productId) {
        return productId == null ? null : productId.value();
    }

    @Override
    public ProductId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new ProductId(dbData);
    }
}
