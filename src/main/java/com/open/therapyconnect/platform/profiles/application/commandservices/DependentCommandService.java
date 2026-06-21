package com.open.therapyconnect.platform.profiles.application.commandservices;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface DependentCommandService {
    Result<Long, ApplicationError> handle(CreateDependentCommand command);
    Result<Dependent, ApplicationError> handle(UpdateDependentCommand command);
    Result<Long, ApplicationError> handle(DeleteDependentCommand command);
}
