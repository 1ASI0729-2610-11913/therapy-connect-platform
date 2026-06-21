package com.open.therapyconnect.platform.coursesandlearning.domain.model.commands;

public record CreateEvaluationCommand(
        Long courseId,
        Long studentId,
        Integer score,
        String feedback,
        String evaluationDate
) {}
