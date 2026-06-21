package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.UpdateProductCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {

    public static UpdateProductCommand toCommandFromResource(Long productId, UpdateProductResource resource) {
        return new UpdateProductCommand(
                productId,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.recommendedFor()
        );
    }
}
