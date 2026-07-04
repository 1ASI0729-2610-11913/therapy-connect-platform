package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.UpdateDependentCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.UpdateDependentResource;

public class UpdateDependentCommandFromResourceAssembler {

    public static UpdateDependentCommand toCommandFromResource(Long dependentId, UpdateDependentResource resource) {
        return new UpdateDependentCommand(
                dependentId,
                resource.dependentName(),
                resource.dependentCondition(),
                resource.needLevel(),
                resource.progressState()
        );
    }
}
