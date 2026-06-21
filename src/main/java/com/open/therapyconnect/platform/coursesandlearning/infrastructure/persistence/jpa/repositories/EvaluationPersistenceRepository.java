package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.EvaluationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationPersistenceRepository extends JpaRepository<EvaluationPersistenceEntity, Long> {}
