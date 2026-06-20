package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.CatalogPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogPersistenceRepository extends JpaRepository<CatalogPersistenceEntity, Long> {
    boolean existsByCatalogName(String name);
}
