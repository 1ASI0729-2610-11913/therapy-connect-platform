package com.open.therapyconnect.platform.marketplace.domain.repositories;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;

import java.util.List;
import java.util.Optional;

public interface DependentRepository {
    Dependent save(Dependent dependent);
    Optional<Dependent> findById(Long id);
    List<Dependent> findAll();
    boolean existsByDependentName(String name);
    boolean existsById(Long id);
    void deleteById(Long id);
}
