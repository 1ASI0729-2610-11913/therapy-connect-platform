package com.open.therapyconnect.platform.marketplace.domain.repositories;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;

import java.util.List;
import java.util.Optional;

/** Product repository port. */
public interface ProductRepository {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    boolean existsById(Long id);
    void deleteById(Long id);
}
