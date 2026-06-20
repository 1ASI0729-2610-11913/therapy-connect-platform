package com.open.therapyconnect.platform.marketplace.application.internal.queryservices;

import com.open.therapyconnect.platform.marketplace.application.queryservices.ProductQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetProductByIdQuery;
import com.open.therapyconnect.platform.marketplace.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return this.productRepository.findById(query.productId());
    }
}
