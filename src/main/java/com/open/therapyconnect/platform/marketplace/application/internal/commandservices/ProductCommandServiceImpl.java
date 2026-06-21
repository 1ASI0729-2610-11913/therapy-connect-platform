package com.open.therapyconnect.platform.marketplace.application.internal.commandservices;

import com.open.therapyconnect.platform.marketplace.application.commandservices.ProductCommandService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.*;
import com.open.therapyconnect.platform.marketplace.domain.repositories.ProductRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateProductCommand command) {
        var product = new Product(command);
        try {
            product = productRepository.save(product);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-product", e.getMessage()));
        }
        return Result.success(product.getId());
    }

    @Override
    public Result<Product, ApplicationError> handle(UpdateProductCommand command) {
        var result = productRepository.findById(command.productId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Product", command.productId().toString()));
        var productToUpdate = result.get();
        try {
            var updatedProduct = productRepository.save(
                    productToUpdate.updateInformation(
                            command.name(),
                            command.description(),
                            command.price(),
                            command.imageUrl(),
                            command.recommendedFor()
                    )
            );
            return Result.success(updatedProduct);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-product", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteProductCommand command) {
        if (!productRepository.existsById(command.productId()))
            return Result.failure(ApplicationError.notFound("Product", command.productId().toString()));
        try {
            productRepository.deleteById(command.productId());
            return Result.success(command.productId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-product", e.getMessage()));
        }
    }
}
