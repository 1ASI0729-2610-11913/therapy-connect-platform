package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources;

public record UpdateEvaluationResource(
        Integer score,
        String feedback,
        String evaluationDate
) {}
