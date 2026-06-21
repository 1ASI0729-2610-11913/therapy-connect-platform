package com.open.therapyconnect.platform.profiles.interfaces.rest.transform;

import com.open.therapyconnect.platform.profiles.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.profiles.interfaces.rest.resources.CreateDependentResource;

public class CreateDependentCommandFromResourceAssembler {

    public static CreateDependentCommand toCommandFromResource(CreateDependentResource resource) {
        return new CreateDependentCommand(
                resource.name(),
                resource.age(),
                resource.condition(),
                resource.parentId()
        );
    }
}
