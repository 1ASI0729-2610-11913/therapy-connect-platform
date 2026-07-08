package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Objects;

public record CatalogId(Long value) {
    public CatalogId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[CatalogId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[CatalogId] value must be greater than zero");
        }
    }

    public CatalogId() { this(null); }
}
