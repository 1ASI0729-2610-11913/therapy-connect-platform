package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.CourseResource;

public class CourseResourceFromEntityAssembler {

    public static CourseResource toResourceFromEntity(Course course) {
        return new CourseResource(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getInstructorName(),
                course.getDuration(),
                course.getLevel(),
                course.getImageUrl()
        );
    }
}
