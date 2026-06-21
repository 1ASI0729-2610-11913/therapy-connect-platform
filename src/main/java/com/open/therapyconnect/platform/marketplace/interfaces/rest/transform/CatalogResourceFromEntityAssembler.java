package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CatalogResource;

public class CatalogResourceFromEntityAssembler {

    public static CatalogResource toResourceFromEntity(Catalog catalog) {
        return new CatalogResource(
                catalog.getId(),
                catalog.getName(),
                catalog.getDescription()
        );
    }
}
