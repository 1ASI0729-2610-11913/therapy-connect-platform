package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.UpdateCatalogCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.UpdateCatalogResource;

public class UpdateCatalogCommandFromResourceAssembler {

    public static UpdateCatalogCommand toCommandFromResource(Long catalogId, UpdateCatalogResource resource) {
        return new UpdateCatalogCommand(
                catalogId,
                resource.name(),
                resource.description()
        );
    }
}
