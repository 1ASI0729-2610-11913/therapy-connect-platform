package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.DependentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentPersistenceRepository extends JpaRepository<DependentPersistenceEntity, Long> {
    boolean existsByDependentName(String name);
}
