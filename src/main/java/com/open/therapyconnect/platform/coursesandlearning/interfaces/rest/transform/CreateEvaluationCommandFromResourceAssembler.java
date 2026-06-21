package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.CreateEvaluationCommand;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.CreateEvaluationResource;

public class CreateEvaluationCommandFromResourceAssembler {

    public static CreateEvaluationCommand toCommandFromResource(CreateEvaluationResource resource) {
        return new CreateEvaluationCommand(
                resource.courseId(),
                resource.studentId(),
                resource.score(),
                resource.feedback(),
                resource.evaluationDate()
        );
    }
}
