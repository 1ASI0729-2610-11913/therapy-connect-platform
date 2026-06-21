package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources;

public record CreateCourseResource(
        String title,
        String description,
        String instructorName,
        String duration,
        String level,
        String imageUrl
) {}
