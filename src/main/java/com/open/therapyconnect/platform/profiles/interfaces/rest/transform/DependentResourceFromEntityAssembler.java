package com.open.therapyconnect.platform.profiles.interfaces.rest.transform;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.interfaces.rest.resources.DependentResource;

public class DependentResourceFromEntityAssembler {

    public static DependentResource toResourceFromEntity(Dependent dependent) {
        return new DependentResource(
                dependent.getId(),
                dependent.getName(),
                dependent.getAge(),
                dependent.getCondition() != null ? dependent.getCondition().name() : null,
                dependent.getParentId()
        );
    }
}
