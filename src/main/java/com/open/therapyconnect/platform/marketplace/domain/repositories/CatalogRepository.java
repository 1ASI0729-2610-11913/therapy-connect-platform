package com.open.therapyconnect.platform.marketplace.domain.repositories;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;

import java.util.List;
import java.util.Optional;

/** Catalog repository port. */
public interface CatalogRepository {
    Optional<Catalog> findById(Long id);
    List<Catalog> findAll();
    Catalog save(Catalog catalog);
    boolean existsById(Long id);
    void deleteById(Long id);
}
