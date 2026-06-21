package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Course;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.CourseRepository;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers.CoursePersistenceAssembler;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories.CoursePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CoursePersistenceRepository coursePersistenceRepository;

    public CourseRepositoryImpl(CoursePersistenceRepository coursePersistenceRepository) {
        this.coursePersistenceRepository = coursePersistenceRepository;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return coursePersistenceRepository.findById(id)
                .map(CoursePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Course> findAll() {
        return coursePersistenceRepository.findAll().stream()
                .map(CoursePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Course save(Course course) {
        var saved = coursePersistenceRepository.save(
                CoursePersistenceAssembler.toPersistenceFromDomain(course));
        return CoursePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return coursePersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        coursePersistenceRepository.deleteById(id);
    }
}
