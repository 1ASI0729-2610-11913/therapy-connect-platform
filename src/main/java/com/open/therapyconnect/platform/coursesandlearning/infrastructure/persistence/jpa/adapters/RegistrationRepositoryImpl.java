package com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.RegistrationRepository;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.assemblers.RegistrationPersistenceAssembler;
import com.open.therapyconnect.platform.coursesandlearning.infrastructure.persistence.jpa.repositories.RegistrationPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {

    private final RegistrationPersistenceRepository registrationPersistenceRepository;

    public RegistrationRepositoryImpl(RegistrationPersistenceRepository registrationPersistenceRepository) {
        this.registrationPersistenceRepository = registrationPersistenceRepository;
    }

    @Override
    public Optional<Registration> findById(Long id) {
        return registrationPersistenceRepository.findById(id)
                .map(RegistrationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Registration> findAll() {
        return registrationPersistenceRepository.findAll().stream()
                .map(RegistrationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Registration save(Registration registration) {
        var saved = registrationPersistenceRepository.save(
                RegistrationPersistenceAssembler.toPersistenceFromDomain(registration));
        return RegistrationPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return registrationPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        registrationPersistenceRepository.deleteById(id);
    }
}
