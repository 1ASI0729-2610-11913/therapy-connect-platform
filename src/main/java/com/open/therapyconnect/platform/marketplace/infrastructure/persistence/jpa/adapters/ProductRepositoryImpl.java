package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.repositories.ProductRepository;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers.ProductPersistenceAssembler;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.repositories.ProductPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductPersistenceRepository productPersistenceRepository;

    public ProductRepositoryImpl(ProductPersistenceRepository productPersistenceRepository) {
        this.productPersistenceRepository = productPersistenceRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productPersistenceRepository.findById(id)
                .map(ProductPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Product> findAll() {
        return productPersistenceRepository.findAll().stream()
                .map(ProductPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Product save(Product product) {
        var saved = productPersistenceRepository.save(
                ProductPersistenceAssembler.toPersistenceFromDomain(product));
        return ProductPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return productPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        productPersistenceRepository.deleteById(id);
    }
}
