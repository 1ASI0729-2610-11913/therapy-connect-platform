package com.open.therapyconnect.platform.coursesandlearning.domain.repositories;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;

import java.util.List;
import java.util.Optional;

/** Course repository port. */
public interface CourseRepository {
    Optional<Course> findById(Long id);
    List<Course> findAll();
    Course save(Course course);
    boolean existsById(Long id);
    void deleteById(Long id);
}
