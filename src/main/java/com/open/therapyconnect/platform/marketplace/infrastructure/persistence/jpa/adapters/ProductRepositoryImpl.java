package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.repositories.ProductRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.ProductPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.ProductPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductPersistenceRepository productPersistenceRepository;
    public ProductRepositoryImpl(ProductPersistenceRepository productPersistenceRepository) {
        this.productPersistenceRepository = productPersistenceRepository;
    }

    @Override
    public Product save(Product product) {
        var persistenceSaved = this.productPersistenceRepository
                .save(ProductPersistenceAssembler.toPersistenceFromDomain(product));
        return ProductPersistenceAssembler.toDomainFromPersistence(persistenceSaved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        var persistence = this.productPersistenceRepository.findById(id);
        return Optional.of(ProductPersistenceAssembler.toDomainFromPersistence(persistence.get()));
    }

    @Override
    public boolean existsByProductName(String name) {
        return this.productPersistenceRepository.existsByProductName(name);
    }
}
