package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.entities.RegistrationPersistenceEntity;

public final class RegistrationPersistenceAssembler {

    private RegistrationPersistenceAssembler() {}

    public static Registration toDomainFromPersistence(RegistrationPersistenceEntity entity) {
        if (entity == null) return null;
        var registration = new Registration();
        registration.setId(entity.getId());
        registration.setCourseId(entity.getCourseId());
        registration.setStudentId(entity.getStudentId());
        registration.setRegistrationDate(entity.getRegistrationDate());
        registration.setStatus(entity.getStatus());
        registration.setProgress(entity.getProgress());
        return registration;
    }

    public static RegistrationPersistenceEntity toPersistenceFromDomain(Registration registration) {
        if (registration == null) return null;
        var entity = new RegistrationPersistenceEntity();
        if (registration.getId() != null) entity.setId(registration.getId());
        entity.setCourseId(registration.getCourseId());
        entity.setStudentId(registration.getStudentId());
        entity.setRegistrationDate(registration.getRegistrationDate());
        entity.setStatus(registration.getStatus());
        entity.setProgress(registration.getProgress());
        return entity;
    }
}
