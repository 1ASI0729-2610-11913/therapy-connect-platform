package com.open.therapyconnect.platform.profiles.domain.model.commands;

public record UpdateDependentCommand(
        Long dependentId,
        String name,
        Integer age,
        String condition
) {}
