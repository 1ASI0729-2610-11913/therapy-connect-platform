package com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.infrastructure.persistence.jpa.entities.DependentPersistenceEntity;

public final class DependentPersistenceAssembler {

    private DependentPersistenceAssembler() {}

    public static Dependent toDomainFromPersistence(DependentPersistenceEntity entity) {
        if (entity == null) return null;
        var dependent = new Dependent();
        dependent.setId(entity.getId());
        dependent.setName(entity.getName());
        dependent.setAge(entity.getAge());
        dependent.setCondition(entity.getCondition());
        dependent.setParentId(entity.getParentId());
        return dependent;
    }

    public static DependentPersistenceEntity toPersistenceFromDomain(Dependent dependent) {
        if (dependent == null) return null;
        var entity = new DependentPersistenceEntity();
        if (dependent.getId() != null) entity.setId(dependent.getId());
        entity.setName(dependent.getName());
        entity.setAge(dependent.getAge());
        entity.setCondition(dependent.getCondition());
        entity.setParentId(dependent.getParentId());
        return entity;
    }
}
