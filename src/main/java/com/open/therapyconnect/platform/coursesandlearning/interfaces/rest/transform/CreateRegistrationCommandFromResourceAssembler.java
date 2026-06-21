package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateRegistrationCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.CreateRegistrationResource;

public class CreateRegistrationCommandFromResourceAssembler {

    public static CreateRegistrationCommand toCommandFromResource(CreateRegistrationResource resource) {
        return new CreateRegistrationCommand(
                resource.courseId(),
                resource.studentId(),
                resource.registrationDate(),
                resource.status(),
                resource.progress()
        );
    }
}
