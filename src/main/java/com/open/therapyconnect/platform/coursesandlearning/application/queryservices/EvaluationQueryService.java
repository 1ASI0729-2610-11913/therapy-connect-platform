package com.open.therapyconnect.platform.coursesandlearning.application.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface EvaluationQueryService {
    Optional<Evaluation> handle(GetEvaluationByIdQuery query);
    List<Evaluation> handle(GetAllEvaluationsQuery query);
}
