package com.open.therapyconnect.platform.coursesandlearning.domain.repositories;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;

import java.util.List;
import java.util.Optional;

/** Evaluation repository port. */
public interface EvaluationRepository {
    Optional<Evaluation> findById(Long id);
    List<Evaluation> findAll();
    Evaluation save(Evaluation evaluation);
    boolean existsById(Long id);
    void deleteById(Long id);
}
