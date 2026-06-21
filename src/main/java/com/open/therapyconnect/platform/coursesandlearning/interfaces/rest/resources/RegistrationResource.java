package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources;

public record RegistrationResource(
        Long id,
        Long courseId,
        Long studentId,
        String registrationDate,
        String status,
        Integer progress
) {}
