package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.UpdateEvaluationCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.UpdateEvaluationResource;

public class UpdateEvaluationCommandFromResourceAssembler {

    public static UpdateEvaluationCommand toCommandFromResource(Long evaluationId, UpdateEvaluationResource resource) {
        return new UpdateEvaluationCommand(
                evaluationId,
                resource.score(),
                resource.feedback(),
                resource.evaluationDate()
        );
    }
}
