package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.CoursePersistenceEntity;

public final class CoursePersistenceAssembler {

    private CoursePersistenceAssembler() {}

    public static Course toDomainFromPersistence(CoursePersistenceEntity entity) {
        if (entity == null) return null;
        var course = new Course();
        course.setId(entity.getId());
        course.setTitle(entity.getTitle());
        course.setDescription(entity.getDescription());
        course.setInstructorName(entity.getInstructorName());
        course.setDuration(entity.getDuration());
        course.setLevel(entity.getLevel());
        course.setImageUrl(entity.getImageUrl());
        return course;
    }

    public static CoursePersistenceEntity toPersistenceFromDomain(Course course) {
        if (course == null) return null;
        var entity = new CoursePersistenceEntity();
        if (course.getId() != null) entity.setId(course.getId());
        entity.setTitle(course.getTitle());
        entity.setDescription(course.getDescription());
        entity.setInstructorName(course.getInstructorName());
        entity.setDuration(course.getDuration());
        entity.setLevel(course.getLevel());
        entity.setImageUrl(course.getImageUrl());
        return entity;
    }
}
