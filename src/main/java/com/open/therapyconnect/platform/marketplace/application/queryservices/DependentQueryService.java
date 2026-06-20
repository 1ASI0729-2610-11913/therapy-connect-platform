package com.open.therapyconnect.platform.marketplace.application.queryservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetDependentByIdQuery;

import java.util.Optional;

public interface DependentQueryService {
    Optional<Dependent> handle(GetDependentByIdQuery query);
}
