package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.CoursePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePersistenceRepository extends JpaRepository<CoursePersistenceEntity, Long> {}
