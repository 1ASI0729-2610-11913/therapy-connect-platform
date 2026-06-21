package com.open.therapyconnect.platform.profiles.interfaces.rest.resources;

public record CreateDependentResource(
        String name,
        Integer age,
        String condition,
        Long parentId
) {}
