package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities.DependentPersistenceEntity;

public class DependentPersistenceAssembler {
    private DependentPersistenceAssembler() {}

    public static DependentPersistenceEntity toPersistenceFromDomain(Dependent dependent) {
        DependentPersistenceEntity dependentPersistenceEntity = new DependentPersistenceEntity();
        dependentPersistenceEntity.setId(dependent.getId());
        dependentPersistenceEntity.setDependentName(dependent.getDependentName());
        dependentPersistenceEntity.setDependentCondition(dependent.getDependentCondition());
        dependentPersistenceEntity.setNeedLevel(dependent.getNeedLevel());
        dependentPersistenceEntity.setProgressState(dependent.getProgressState());
        return dependentPersistenceEntity;
    }

    public static Dependent toDomainFromPersistence(DependentPersistenceEntity dependentPersistenceEntity) {
        Dependent dependent = new Dependent();
        dependent.setId(dependentPersistenceEntity.getId());
        dependent.setDependentName(dependentPersistenceEntity.getDependentName());
        dependent.setDependentCondition(dependentPersistenceEntity.getDependentCondition());
        dependent.setNeedLevel(dependentPersistenceEntity.getNeedLevel());
        dependent.setProgressState(dependentPersistenceEntity.getProgressState());
        return dependent;
    }
}
