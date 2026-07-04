package com.open.therapyconnect.platform.marketplace.application.commandservices;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.DeleteDependentCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.UpdateDependentCommand;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface DependentCommandService {
    Result<Long, ApplicationError> handle(CreateDependentCommand command);
    Result<Dependent, ApplicationError> handle(UpdateDependentCommand command);
    Result<Long, ApplicationError> handle(DeleteDependentCommand command);
}
