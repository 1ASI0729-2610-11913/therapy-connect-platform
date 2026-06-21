package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.UpdateRegistrationCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.UpdateRegistrationResource;

public class UpdateRegistrationCommandFromResourceAssembler {

    public static UpdateRegistrationCommand toCommandFromResource(Long registrationId, UpdateRegistrationResource resource) {
        return new UpdateRegistrationCommand(
                registrationId,
                resource.status(),
                resource.progress()
        );
    }
}
