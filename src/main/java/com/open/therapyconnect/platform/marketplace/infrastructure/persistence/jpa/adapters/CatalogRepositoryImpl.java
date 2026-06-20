package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.repositories.CatalogRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.CatalogPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.CatalogPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {
    private final CatalogPersistenceRepository catalogPersistenceRepository;
    public CatalogRepositoryImpl(CatalogPersistenceRepository catalogPersistenceRepository) {
        this.catalogPersistenceRepository = catalogPersistenceRepository;
    }

    @Override
    public Catalog save(Catalog catalog) {
        var persistenceSaved = this.catalogPersistenceRepository
                .save(CatalogPersistenceAssembler.toPersistenceFromDomain(catalog));
        return CatalogPersistenceAssembler.toDomainFromPersistence(persistenceSaved);
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        var persistence = this.catalogPersistenceRepository.findById(id);
        return Optional.of(CatalogPersistenceAssembler.toDomainFromPersistence(persistence.get()));
    }

    @Override
    public boolean existsByCatalogName(String name) {
        return this.catalogPersistenceRepository.existsByCatalogName(name);
    }
}
