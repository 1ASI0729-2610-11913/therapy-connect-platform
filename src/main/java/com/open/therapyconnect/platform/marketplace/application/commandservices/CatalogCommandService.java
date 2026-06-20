package com.open.therapyconnect.platform.marketplace.application.commandservices;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateCatalogCommand;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface CatalogCommandService {
    Result<Long, ApplicationError> handle(CreateCatalogCommand command);
}
