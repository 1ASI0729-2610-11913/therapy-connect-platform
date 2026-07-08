package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Objects;

public record DependentId(Long value) {
    public DependentId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[DependentId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[DependentId] value must be greater than zero");
        }
    }

    public DependentId() { this(null); }
}
