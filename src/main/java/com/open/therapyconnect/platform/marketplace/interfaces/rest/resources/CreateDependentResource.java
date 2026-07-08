package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.ProgressStates;

public record CreateDependentResource(String dependentName, String dependentCondition, String needLevel,
                                      @JsonAlias("progressSate") String progressState) {
        public CreateDependentResource {
                if (dependentCondition == null || dependentCondition.isBlank()) {
                        throw new IllegalArgumentException("dependentCondition cannot be null or blank");
                }
                if (needLevel == null || needLevel.isBlank()) {
                        throw new IllegalArgumentException("needLevel cannot be null or blank");
                }
                if (progressState == null || progressState.isBlank()) {
                        throw new IllegalArgumentException("progressState cannot be null or blank");
                }
                ProgressStates.fromString(progressState);
                if (dependentName == null || dependentName.isBlank()) {
                        dependentName = dependentCondition;
                }
        }
}
