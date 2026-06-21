package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.EvaluationRepository;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers.EvaluationPersistenceAssembler;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories.EvaluationPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EvaluationRepositoryImpl implements EvaluationRepository {

    private final EvaluationPersistenceRepository evaluationPersistenceRepository;

    public EvaluationRepositoryImpl(EvaluationPersistenceRepository evaluationPersistenceRepository) {
        this.evaluationPersistenceRepository = evaluationPersistenceRepository;
    }

    @Override
    public Optional<Evaluation> findById(Long id) {
        return evaluationPersistenceRepository.findById(id)
                .map(EvaluationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationPersistenceRepository.findAll().stream()
                .map(EvaluationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        var saved = evaluationPersistenceRepository.save(
                EvaluationPersistenceAssembler.toPersistenceFromDomain(evaluation));
        return EvaluationPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return evaluationPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        evaluationPersistenceRepository.deleteById(id);
    }
}
