package com.open.therapyconnect.platform.profiles.domain.repositories;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;

import java.util.List;
import java.util.Optional;

/** Dependent repository port. */
public interface DependentRepository {
    Optional<Dependent> findById(Long id);
    List<Dependent> findAll();
    Dependent save(Dependent dependent);
    boolean existsById(Long id);
    void deleteById(Long id);
}
