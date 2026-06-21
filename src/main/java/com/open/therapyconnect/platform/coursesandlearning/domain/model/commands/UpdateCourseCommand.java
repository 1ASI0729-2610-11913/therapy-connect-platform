package com.open.therapyconnect.platform.coursesandlearning.domain.model.commands;

public record UpdateCourseCommand(
        Long courseId,
        String title,
        String description,
        String instructorName,
        String duration,
        String level,
        String imageUrl
) {}
