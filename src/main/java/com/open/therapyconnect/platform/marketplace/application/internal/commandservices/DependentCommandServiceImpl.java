package com.open.therapyconnect.platform.marketplace.application.internal.commandservices;

import com.open.therapyconnect.platform.marketplace.application.commandservices.DependentCommandService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateDependentCommand;
import com.open.therapyconnect.platform.marketplace.domain.repositories.DependentRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class DependentCommandServiceImpl implements DependentCommandService {
    private DependentRepository dependentRepository;
    public DependentCommandServiceImpl(DependentRepository dependentRepository) {
        this.dependentRepository = dependentRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateDependentCommand command) {
        if (this.dependentRepository.existsByDependentName(command.dependentName())) {
            return Result.failure(
                    ApplicationError.conflict("Dependent", "No se permite registrar el dependiente ya existente")
            );
        }
        var dependent = new Dependent(command);
        try {
            dependent = dependentRepository.save(dependent);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-dependent", e.getMessage()));
        }
        return Result.success(dependent.getId());
    }
}
