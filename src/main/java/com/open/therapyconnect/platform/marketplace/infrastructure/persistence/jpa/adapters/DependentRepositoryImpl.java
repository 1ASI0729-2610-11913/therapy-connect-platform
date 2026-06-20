package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.repositories.DependentRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.DependentPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.DependentPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DependentRepositoryImpl implements DependentRepository {
    private final DependentPersistenceRepository dependentPersistenceRepository;
    public DependentRepositoryImpl(DependentPersistenceRepository dependentPersistenceRepository) {
        this.dependentPersistenceRepository = dependentPersistenceRepository;
    }

    @Override
    public Dependent save(Dependent dependent) {
        var persistenceSaved = this.dependentPersistenceRepository
                .save(DependentPersistenceAssembler.toPersistenceFromDomain(dependent));
        return DependentPersistenceAssembler.toDomainFromPersistence(persistenceSaved);
    }

    @Override
    public Optional<Dependent> findById(Long id) {
        var persistence = this.dependentPersistenceRepository.findById(id);
        return Optional.of(DependentPersistenceAssembler.toDomainFromPersistence(persistence.get()));
    }

    @Override
    public boolean existsByDependentName(String name) {
        return this.dependentPersistenceRepository.existsByDependentName(name);
    }
}
