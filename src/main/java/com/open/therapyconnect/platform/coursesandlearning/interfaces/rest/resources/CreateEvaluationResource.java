package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources;

public record CreateEvaluationResource(
        Long courseId,
        Long studentId,
        Integer score,
        String feedback,
        String evaluationDate
) {}
