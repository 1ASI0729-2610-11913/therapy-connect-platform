package com.open.therapyconnect.platform.marketplace.application.commandservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface ProductCommandService {
    Result<Long, ApplicationError> handle(CreateProductCommand command);
    Result<Product, ApplicationError> handle(UpdateProductCommand command);
    Result<Long, ApplicationError> handle(DeleteProductCommand command);
}
