package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.DependentResource;

public class DependentResourceFromEntityAssembler {
    public static DependentResource toResourceFromEntity(Dependent entity) {
        return new DependentResource(
                entity.getId(),
                entity.getDependentName(),
                entity.getDependentCondition(),
                entity.getNeedLevel(),
                entity.getProgressState().name(),
                entity.getProgressState().name()
        );
    }
}
