package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateCatalogCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateCatalogResource;

public class CreateCatalogCommandFromResourceAssembler {

    public static CreateCatalogCommand toCommandFromResource(CreateCatalogResource resource) {
        return new CreateCatalogCommand(
                resource.name(),
                resource.description()
        );
    }
}
