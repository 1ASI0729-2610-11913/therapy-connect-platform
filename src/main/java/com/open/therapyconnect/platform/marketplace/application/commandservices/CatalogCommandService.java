package com.open.therapyconnect.platform.marketplace.application.commandservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface CatalogCommandService {
    Result<Long, ApplicationError> handle(CreateCatalogCommand command);
    Result<Catalog, ApplicationError> handle(UpdateCatalogCommand command);
    Result<Long, ApplicationError> handle(DeleteCatalogCommand command);
}
