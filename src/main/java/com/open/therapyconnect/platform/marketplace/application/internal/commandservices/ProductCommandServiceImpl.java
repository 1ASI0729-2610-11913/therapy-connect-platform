package com.open.therapyconnect.platform.marketplace.application.internal.commandservices;

import com.open.therapyconnect.platform.marketplace.application.commandservices.ProductCommandService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.marketplace.domain.repositories.ProductRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private ProductRepository productRepository;
    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateProductCommand command) {
        if (this.productRepository.existsByProductName(command.productName())) {
            return Result.failure(
                    ApplicationError.conflict("Product", "No se permite registrar el producto ya existente")
            );
        }
        var product = new Product(command);
        try {
            product = productRepository.save(product);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-product", e.getMessage()));
        }
        return Result.success(product.getId());
    }
}
