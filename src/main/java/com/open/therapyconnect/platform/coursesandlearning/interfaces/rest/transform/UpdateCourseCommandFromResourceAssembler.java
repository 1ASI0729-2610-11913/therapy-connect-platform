package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.UpdateCourseCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.UpdateCourseResource;

public class UpdateCourseCommandFromResourceAssembler {

    public static UpdateCourseCommand toCommandFromResource(Long courseId, UpdateCourseResource resource) {
        return new UpdateCourseCommand(
                courseId,
                resource.title(),
                resource.description(),
                resource.instructorName(),
                resource.duration(),
                resource.level(),
                resource.imageUrl()
        );
    }
}
