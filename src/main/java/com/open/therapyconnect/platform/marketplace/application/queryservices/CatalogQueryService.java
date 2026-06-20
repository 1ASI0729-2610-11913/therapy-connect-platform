package com.open.therapyconnect.platform.marketplace.application.queryservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetCatalogByIdQuery;

import java.util.Optional;

public interface CatalogQueryService {
    Optional<Catalog> handle(GetCatalogByIdQuery query);
}
