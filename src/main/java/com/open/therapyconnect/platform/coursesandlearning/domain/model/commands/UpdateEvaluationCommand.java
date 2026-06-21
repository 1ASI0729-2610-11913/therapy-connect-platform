package com.open.therapyconnect.platform.coursesandlearning.domain.model.commands;

public record UpdateEvaluationCommand(
        Long evaluationId,
        Integer score,
        String feedback,
        String evaluationDate
) {}
