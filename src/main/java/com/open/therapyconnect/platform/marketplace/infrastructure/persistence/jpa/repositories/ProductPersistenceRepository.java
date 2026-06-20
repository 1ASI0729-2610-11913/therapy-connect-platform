package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.ProductPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPersistenceRepository extends JpaRepository<ProductPersistenceEntity, Long> {
    boolean existsByProductName(String name);
}
