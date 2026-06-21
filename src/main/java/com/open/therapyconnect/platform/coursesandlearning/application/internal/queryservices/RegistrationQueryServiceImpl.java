package com.open.therapyconnect.platform.coursesandlearning.application.internal.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.RegistrationQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationQueryServiceImpl implements RegistrationQueryService {

    private final RegistrationRepository registrationRepository;

    public RegistrationQueryServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Optional<Registration> handle(GetRegistrationByIdQuery query) {
        return registrationRepository.findById(query.registrationId());
    }

    @Override
    public List<Registration> handle(GetAllRegistrationsQuery query) {
        return registrationRepository.findAll();
    }
}
