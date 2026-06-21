package com.open.therapyconnect.platform.coursesandlearning.application.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface EvaluationCommandService {
    Result<Long, ApplicationError> handle(CreateEvaluationCommand command);
    Result<Evaluation, ApplicationError> handle(UpdateEvaluationCommand command);
    Result<Long, ApplicationError> handle(DeleteEvaluationCommand command);
}
