package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.RegistrationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationPersistenceRepository extends JpaRepository<RegistrationPersistenceEntity, Long> {}
