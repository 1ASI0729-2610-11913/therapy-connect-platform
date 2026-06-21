package com.open.therapyconnect.platform.coursesandlearning.application.internal.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.CourseQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    private final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.courseId());
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return courseRepository.findAll();
    }
}
