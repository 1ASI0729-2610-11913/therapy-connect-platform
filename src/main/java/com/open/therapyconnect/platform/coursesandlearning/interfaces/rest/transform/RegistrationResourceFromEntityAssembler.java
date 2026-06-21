package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.RegistrationResource;

public class RegistrationResourceFromEntityAssembler {

    public static RegistrationResource toResourceFromEntity(Registration registration) {
        return new RegistrationResource(
                registration.getId(),
                registration.getCourseId(),
                registration.getStudentId(),
                registration.getRegistrationDate(),
                registration.getStatus().name(),
                registration.getProgress()
        );
    }
}
