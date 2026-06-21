package com.open.therapyconnect.platform.profiles.application.internal.commandservices;

import com.open.therapyconnect.platform.profiles.application.commandservices.DependentCommandService;
import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.domain.model.commands.*;
import com.open.therapyconnect.platform.profiles.domain.repositories.DependentRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class DependentCommandServiceImpl implements DependentCommandService {

    private final DependentRepository dependentRepository;

    public DependentCommandServiceImpl(DependentRepository dependentRepository) {
        this.dependentRepository = dependentRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateDependentCommand command) {
        var dependent = new Dependent(command);
        try {
            dependent = dependentRepository.save(dependent);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-dependent", e.getMessage()));
        }
        return Result.success(dependent.getId());
    }

    @Override
    public Result<Dependent, ApplicationError> handle(UpdateDependentCommand command) {
        var result = dependentRepository.findById(command.dependentId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Dependent", command.dependentId().toString()));
        var dependentToUpdate = result.get();
        try {
            var updatedDependent = dependentRepository.save(
                    dependentToUpdate.updateInformation(command.name(), command.age(), command.condition())
            );
            return Result.success(updatedDependent);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-dependent", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteDependentCommand command) {
        if (!dependentRepository.existsById(command.dependentId()))
            return Result.failure(ApplicationError.notFound("Dependent", command.dependentId().toString()));
        try {
            dependentRepository.deleteById(command.dependentId());
            return Result.success(command.dependentId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-dependent", e.getMessage()));
        }
    }
}
