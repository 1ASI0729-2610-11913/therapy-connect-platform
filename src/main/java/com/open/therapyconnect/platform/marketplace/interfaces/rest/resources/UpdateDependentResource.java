package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UpdateDependentResource(
        String dependentName,
        String dependentCondition,
        String needLevel,
        @JsonAlias("progressSate") String progressState
) {}
