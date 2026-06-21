package com.open.therapyconnect.platform.coursesandlearning.application.internal.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.EvaluationQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationQueryServiceImpl implements EvaluationQueryService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationQueryServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Optional<Evaluation> handle(GetEvaluationByIdQuery query) {
        return evaluationRepository.findById(query.evaluationId());
    }

    @Override
    public List<Evaluation> handle(GetAllEvaluationsQuery query) {
        return evaluationRepository.findAll();
    }
}
