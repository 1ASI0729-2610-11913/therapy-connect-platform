package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.EvaluationResource;

public class EvaluationResourceFromEntityAssembler {

    public static EvaluationResource toResourceFromEntity(Evaluation evaluation) {
        return new EvaluationResource(
                evaluation.getId(),
                evaluation.getCourseId(),
                evaluation.getStudentId(),
                evaluation.getScore(),
                evaluation.getFeedback(),
                evaluation.getEvaluationDate()
        );
    }
}
