package com.open.therapyconnect.platform.marketplace.application.commandservices;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface ProductCommandService {
    Result<Long, ApplicationError> handle(CreateProductCommand command);
}
