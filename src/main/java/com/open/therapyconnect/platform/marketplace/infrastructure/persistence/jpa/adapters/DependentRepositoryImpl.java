package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.repositories.DependentRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.DependentPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.DependentPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        return this.dependentPersistenceRepository.findById(id)
                .map(DependentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Dependent> findAll() {
        return this.dependentPersistenceRepository.findAll().stream()
                .map(DependentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public boolean existsByDependentName(String name) {
        return this.dependentPersistenceRepository.existsByDependentName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return this.dependentPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.dependentPersistenceRepository.deleteById(id);
    }
}
