package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources;

public record CreateRegistrationResource(
        Long courseId,
        Long studentId,
        String registrationDate,
        String status,
        Integer progress
) {}
