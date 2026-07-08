package com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.entities.DependentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("profileDependentPersistenceRepository")
public interface DependentPersistenceRepository extends JpaRepository<DependentPersistenceEntity, Long> {}
