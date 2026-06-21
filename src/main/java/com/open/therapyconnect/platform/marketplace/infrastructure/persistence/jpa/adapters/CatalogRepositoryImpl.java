package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.repositories.CatalogRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.CatalogPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.CatalogPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    private final CatalogPersistenceRepository catalogPersistenceRepository;

    public CatalogRepositoryImpl(CatalogPersistenceRepository catalogPersistenceRepository) {
        this.catalogPersistenceRepository = catalogPersistenceRepository;
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogPersistenceRepository.findById(id)
                .map(CatalogPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogPersistenceRepository.findAll().stream()
                .map(CatalogPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Catalog save(Catalog catalog) {
        var saved = catalogPersistenceRepository.save(
                CatalogPersistenceAssembler.toPersistenceFromDomain(catalog));
        return CatalogPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return catalogPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        catalogPersistenceRepository.deleteById(id);
    }
}
