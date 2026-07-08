package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Objects;

public record ProductId(Long value) {
    public ProductId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[ProductId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[ProductId] value must be greater than zero");
        }
    }

    public ProductId() { this(null); }
}
