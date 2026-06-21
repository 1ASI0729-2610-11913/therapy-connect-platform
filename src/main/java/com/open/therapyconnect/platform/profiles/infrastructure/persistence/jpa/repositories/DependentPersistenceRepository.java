package com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.entities.DependentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentPersistenceRepository extends JpaRepository<DependentPersistenceEntity, Long> {}
