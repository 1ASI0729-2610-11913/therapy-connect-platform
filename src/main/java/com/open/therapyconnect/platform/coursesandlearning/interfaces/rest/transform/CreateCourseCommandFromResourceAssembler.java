package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateCourseCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.CreateCourseResource;

public class CreateCourseCommandFromResourceAssembler {

    public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {
        return new CreateCourseCommand(
                resource.title(),
                resource.description(),
                resource.instructorName(),
                resource.duration(),
                resource.level(),
                resource.imageUrl()
        );
    }
}
