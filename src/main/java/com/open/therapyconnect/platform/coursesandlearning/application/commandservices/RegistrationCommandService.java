package com.open.therapyconnect.platform.coursesandlearning.application.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface RegistrationCommandService {
    Result<Long, ApplicationError> handle(CreateRegistrationCommand command);
    Result<Registration, ApplicationError> handle(UpdateRegistrationCommand command);
    Result<Long, ApplicationError> handle(DeleteRegistrationCommand command);
}
