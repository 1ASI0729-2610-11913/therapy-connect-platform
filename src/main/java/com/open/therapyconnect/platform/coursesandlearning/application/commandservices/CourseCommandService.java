package com.open.therapyconnect.platform.coursesandlearning.application.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface CourseCommandService {
    Result<Long, ApplicationError> handle(CreateCourseCommand command);
    Result<Course, ApplicationError> handle(UpdateCourseCommand command);
    Result<Long, ApplicationError> handle(DeleteCourseCommand command);
}
