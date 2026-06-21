package com.open.therapyconnect.platform.profiles.domain.model.commands;

public record CreateDependentCommand(
        String name,
        Integer age,
        String condition,
        Long parentId
) {}
