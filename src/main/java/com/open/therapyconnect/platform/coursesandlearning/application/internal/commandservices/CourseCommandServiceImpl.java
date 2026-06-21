package com.open.therapyconnect.platform.coursesandlearning.application.internal.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.CourseCommandService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.CourseRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateCourseCommand command) {
        var course = new Course(command);
        try {
            course = courseRepository.save(course);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-course", e.getMessage()));
        }
        return Result.success(course.getId());
    }

    @Override
    public Result<Course, ApplicationError> handle(UpdateCourseCommand command) {
        var result = courseRepository.findById(command.courseId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Course", command.courseId().toString()));
        var courseToUpdate = result.get();
        try {
            var updatedCourse = courseRepository.save(
                    courseToUpdate.updateInformation(
                            command.title(),
                            command.description(),
                            command.instructorName(),
                            command.duration(),
                            command.level(),
                            command.imageUrl()
                    )
            );
            return Result.success(updatedCourse);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-course", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteCourseCommand command) {
        if (!courseRepository.existsById(command.courseId()))
            return Result.failure(ApplicationError.notFound("Course", command.courseId().toString()));
        try {
            courseRepository.deleteById(command.courseId());
            return Result.success(command.courseId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-course", e.getMessage()));
        }
    }
}
