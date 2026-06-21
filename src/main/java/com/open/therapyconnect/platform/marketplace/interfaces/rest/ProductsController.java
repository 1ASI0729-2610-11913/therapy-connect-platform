package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.ProductCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.ProductQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.DeleteProductCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetAllProductsQuery;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetProductByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product management endpoints")
public class ProductsController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductResource resource) {
        var command = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = productCommandService.handle(command)
                .flatMap(productId -> productQueryService.handle(new GetProductByIdQuery(productId))
                        .<Result<com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Product", productId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ProductResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId) {
        var query = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(query);
        if (product.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProductResourceFromEntityAssembler.toResourceFromEntity(product.get()));
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var products = productQueryService.handle(new GetAllProductsQuery());
        var resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update product")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource resource) {
        var command = UpdateProductCommandFromResourceAssembler.toCommandFromResource(productId, resource);
        var result = productCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ProductResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete product")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        var command = new DeleteProductCommand(productId);
        var result = productCommandService.handle(command)
                .map(deletedId -> new MessageResource("Product with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
