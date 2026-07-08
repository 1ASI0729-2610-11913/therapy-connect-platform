package com.open.therapyconnect.platform.marketplace.domain.model.queries;

public record GetDependentByIdQuery(Long dependentId) {
    public GetDependentByIdQuery {
        if (dependentId == null || dependentId < 0)
            throw new IllegalArgumentException("dependentId is required");
    }
}
