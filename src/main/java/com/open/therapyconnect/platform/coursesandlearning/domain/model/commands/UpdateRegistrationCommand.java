package com.open.therapyconnect.platform.coursesandlearning.domain.model.commands;

public record UpdateRegistrationCommand(
        Long registrationId,
        String status,
        Integer progress
) {}
