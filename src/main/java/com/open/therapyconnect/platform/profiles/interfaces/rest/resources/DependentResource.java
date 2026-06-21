package com.open.therapyconnect.platform.profiles.interfaces.rest.resources;

public record DependentResource(
        Long id,
        String name,
        Integer age,
        String condition,
        Long parentId
) {}
