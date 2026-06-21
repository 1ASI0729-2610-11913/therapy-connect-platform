package com.open.therapyconnect.platform.coursesandlearning.application.internal.commandservices;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.RegistrationCommandService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.*;
import com.open.therapyconnect.platform.coursesandlearning.domain.repositories.RegistrationRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class RegistrationCommandServiceImpl implements RegistrationCommandService {

    private final RegistrationRepository registrationRepository;

    public RegistrationCommandServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateRegistrationCommand command) {
        var registration = new Registration(command);
        try {
            registration = registrationRepository.save(registration);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-registration", e.getMessage()));
        }
        return Result.success(registration.getId());
    }

    @Override
    public Result<Registration, ApplicationError> handle(UpdateRegistrationCommand command) {
        var result = registrationRepository.findById(command.registrationId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Registration", command.registrationId().toString()));
        var registrationToUpdate = result.get();
        try {
            var updatedRegistration = registrationRepository.save(
                    registrationToUpdate.updateInformation(command.status(), command.progress())
            );
            return Result.success(updatedRegistration);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-registration", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteRegistrationCommand command) {
        if (!registrationRepository.existsById(command.registrationId()))
            return Result.failure(ApplicationError.notFound("Registration", command.registrationId().toString()));
        try {
            registrationRepository.deleteById(command.registrationId());
            return Result.success(command.registrationId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-registration", e.getMessage()));
        }
    }
}
