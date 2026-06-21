package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {

    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.catalogId(),
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.recommendedFor()
        );
    }
}
