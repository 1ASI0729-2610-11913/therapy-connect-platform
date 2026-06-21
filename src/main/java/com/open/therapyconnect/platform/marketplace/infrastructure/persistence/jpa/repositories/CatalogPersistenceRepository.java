package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.CatalogPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogPersistenceRepository extends JpaRepository<CatalogPersistenceEntity, Long> {}
