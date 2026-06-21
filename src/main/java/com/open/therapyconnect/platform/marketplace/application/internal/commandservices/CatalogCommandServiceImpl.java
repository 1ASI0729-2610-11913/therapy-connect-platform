package com.open.therapyconnect.platform.marketplace.application.internal.commandservices;

import com.open.therapyconnect.platform.marketplace.application.commandservices.CatalogCommandService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.*;
import com.open.therapyconnect.platform.marketplace.domain.repositories.CatalogRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class CatalogCommandServiceImpl implements CatalogCommandService {

    private final CatalogRepository catalogRepository;

    public CatalogCommandServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateCatalogCommand command) {
        var catalog = new Catalog(command);
        try {
            catalog = catalogRepository.save(catalog);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-catalog", e.getMessage()));
        }
        return Result.success(catalog.getId());
    }

    @Override
    public Result<Catalog, ApplicationError> handle(UpdateCatalogCommand command) {
        var result = catalogRepository.findById(command.catalogId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Catalog", command.catalogId().toString()));
        var catalogToUpdate = result.get();
        try {
            var updatedCatalog = catalogRepository.save(
                    catalogToUpdate.updateInformation(command.name(), command.description())
            );
            return Result.success(updatedCatalog);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-catalog", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteCatalogCommand command) {
        if (!catalogRepository.existsById(command.catalogId()))
            return Result.failure(ApplicationError.notFound("Catalog", command.catalogId().toString()));
        try {
            catalogRepository.deleteById(command.catalogId());
            return Result.success(command.catalogId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-catalog", e.getMessage()));
        }
    }
}
