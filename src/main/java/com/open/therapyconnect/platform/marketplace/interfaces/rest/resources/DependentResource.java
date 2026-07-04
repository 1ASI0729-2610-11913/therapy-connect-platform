package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

public record DependentResource(Long id, String dependentName, String dependentCondition,
                                String needLevel, String progressState, String progressSate
) {
}
