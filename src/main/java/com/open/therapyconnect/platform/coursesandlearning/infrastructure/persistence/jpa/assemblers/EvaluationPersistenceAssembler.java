package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.EvaluationPersistenceEntity;

public final class EvaluationPersistenceAssembler {

    private EvaluationPersistenceAssembler() {}

    public static Evaluation toDomainFromPersistence(EvaluationPersistenceEntity entity) {
        if (entity == null) return null;
        var evaluation = new Evaluation();
        evaluation.setId(entity.getId());
        evaluation.setCourseId(entity.getCourseId());
        evaluation.setStudentId(entity.getStudentId());
        evaluation.setScore(entity.getScore());
        evaluation.setFeedback(entity.getFeedback());
        evaluation.setEvaluationDate(entity.getEvaluationDate());
        return evaluation;
    }

    public static EvaluationPersistenceEntity toPersistenceFromDomain(Evaluation evaluation) {
        if (evaluation == null) return null;
        var entity = new EvaluationPersistenceEntity();
        if (evaluation.getId() != null) entity.setId(evaluation.getId());
        entity.setCourseId(evaluation.getCourseId());
        entity.setStudentId(evaluation.getStudentId());
        entity.setScore(evaluation.getScore());
        entity.setFeedback(evaluation.getFeedback());
        entity.setEvaluationDate(evaluation.getEvaluationDate());
        return entity;
    }
}
