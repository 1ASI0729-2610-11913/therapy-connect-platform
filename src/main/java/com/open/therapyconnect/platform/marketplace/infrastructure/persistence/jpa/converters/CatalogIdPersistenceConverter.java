package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.converters;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.CatalogId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CatalogIdPersistenceConverter implements AttributeConverter<CatalogId, Long> {
    @Override
    public Long convertToDatabaseColumn(CatalogId catalogId) {
        return catalogId == null ? null : catalogId.value();
    }

    @Override
    public CatalogId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new CatalogId(dbData);
    }
}
