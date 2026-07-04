package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record UpdateDependentCommand(
        Long dependentId,
        String dependentName,
        String dependentCondition,
        String needLevel,
        String progressState
) {}
