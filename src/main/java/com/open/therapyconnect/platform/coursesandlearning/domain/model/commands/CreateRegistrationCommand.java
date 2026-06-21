package com.open.therapyconnect.platform.coursesandlearning.domain.model.commands;

public record CreateRegistrationCommand(
        Long courseId,
        Long studentId,
        String registrationDate,
        String status,
        Integer progress
) {}
