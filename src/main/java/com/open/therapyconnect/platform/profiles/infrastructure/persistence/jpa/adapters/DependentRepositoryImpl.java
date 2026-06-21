package com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.domain.repositories.DependentRepository;
import com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.assemblers.DependentPersistenceAssembler;
import com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.repositories.DependentPersistenceRepository;
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
    public Optional<Dependent> findById(Long id) {
        return dependentPersistenceRepository.findById(id)
                .map(DependentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Dependent> findAll() {
        return dependentPersistenceRepository.findAll().stream()
                .map(DependentPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Dependent save(Dependent dependent) {
        var saved = dependentPersistenceRepository.save(
                DependentPersistenceAssembler.toPersistenceFromDomain(dependent));
        return DependentPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return dependentPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        dependentPersistenceRepository.deleteById(id);
    }
}
