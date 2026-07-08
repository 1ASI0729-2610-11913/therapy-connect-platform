package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateDependentResource;

public class CreateDependentCommandFromResourceAssembler {
    public static CreateDependentCommand toCommandFromResource(CreateDependentResource resource) {
        return new CreateDependentCommand(
                resource.dependentName(),
                resource.dependentCondition(),
                resource.needLevel(),
                resource.progressState()
        );
    }
}
