package com.open.therapyconnect.platform.coursesandlearning.application.internal.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.EvaluationCommandService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.EvaluationRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class EvaluationCommandServiceImpl implements EvaluationCommandService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationCommandServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateEvaluationCommand command) {
        var evaluation = new Evaluation(command);
        try {
            evaluation = evaluationRepository.save(evaluation);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-evaluation", e.getMessage()));
        }
        return Result.success(evaluation.getId());
    }

    @Override
    public Result<Evaluation, ApplicationError> handle(UpdateEvaluationCommand command) {
        var result = evaluationRepository.findById(command.evaluationId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Evaluation", command.evaluationId().toString()));
        var evaluationToUpdate = result.get();
        try {
            var updatedEvaluation = evaluationRepository.save(
                    evaluationToUpdate.updateInformation(command.score(), command.feedback(), command.evaluationDate())
            );
            return Result.success(updatedEvaluation);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-evaluation", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteEvaluationCommand command) {
        if (!evaluationRepository.existsById(command.evaluationId()))
            return Result.failure(ApplicationError.notFound("Evaluation", command.evaluationId().toString()));
        try {
            evaluationRepository.deleteById(command.evaluationId());
            return Result.success(command.evaluationId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-evaluation", e.getMessage()));
        }
    }
}
