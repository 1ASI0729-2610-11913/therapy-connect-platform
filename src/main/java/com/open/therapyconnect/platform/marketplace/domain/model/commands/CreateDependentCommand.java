package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record CreateDependentCommand(String dependentName, String dependentCondition, String needLevel, String progressState) {
    public CreateDependentCommand {
        if (dependentName == null)
            throw new IllegalArgumentException("dependentName cannot be null or blank");
        if (dependentCondition == null)
            throw new IllegalArgumentException("Dependent condition cannot be null or blank");
        if (needLevel == null)
            throw new IllegalArgumentException("NeedLevel cannot be null or blank");
    }
}
