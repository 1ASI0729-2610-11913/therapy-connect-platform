package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.converters;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.DependentId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DependentIdPersistenceConverter implements AttributeConverter<DependentId, Long> {
    @Override
    public Long convertToDatabaseColumn(DependentId dependentId) {
        return dependentId == null ? null : dependentId.value();
    }

    @Override
    public DependentId convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new DependentId(dbData);
    }
}
