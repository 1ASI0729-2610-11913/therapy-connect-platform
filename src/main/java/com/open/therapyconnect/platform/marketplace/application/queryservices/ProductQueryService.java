package com.open.therapyconnect.platform.marketplace.application.queryservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByIdQuery query);
    List<Product> handle(GetAllProductsQuery query);
}
