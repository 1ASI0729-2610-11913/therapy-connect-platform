package com.open.therapyconnect.platform.marketplace.application.internal.commandservices;

import com.open.therapyconnect.platform.marketplace.application.commandservices.CatalogCommandService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateCatalogCommand;
import com.open.therapyconnect.platform.marketplace.domain.repositories.CatalogRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class CatalogCommandServiceImpl implements CatalogCommandService {
    private CatalogRepository catalogRepository;
    public CatalogCommandServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateCatalogCommand command) {
        if (this.catalogRepository.existsByCatalogName(command.catalogName())) {
            return Result.failure(
                    ApplicationError.conflict("Catalog", "No se permite registrar el catalogo ya existente")
            );
        }
        var catalog = new Catalog(command);
        try {
            catalog = catalogRepository.save(catalog);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-catalog", e.getMessage()));
        }
        return Result.success(catalog.getId());
    }
}
