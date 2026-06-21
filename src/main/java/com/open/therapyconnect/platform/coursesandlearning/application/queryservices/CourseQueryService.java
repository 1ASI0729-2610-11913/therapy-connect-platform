package com.open.therapyconnect.platform.coursesandlearning.application.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
    Optional<Course> handle(GetCourseByIdQuery query);
    List<Course> handle(GetAllCoursesQuery query);
}
