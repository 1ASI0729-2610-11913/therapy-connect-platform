package com.open.therapyconnect.platform.profiles.interfaces.rest.transform;

import com.open.therapyconnect.platform.profiles.domain.model.commands.UpdateDependentCommand;
import com.open.therapyconnect.platform.profiles.interfaces.rest.resources.UpdateDependentResource;

public class UpdateDependentCommandFromResourceAssembler {

    public static UpdateDependentCommand toCommandFromResource(Long dependentId, UpdateDependentResource resource) {
        return new UpdateDependentCommand(
                dependentId,
                resource.name(),
                resource.age(),
                resource.condition()
        );
    }
}
