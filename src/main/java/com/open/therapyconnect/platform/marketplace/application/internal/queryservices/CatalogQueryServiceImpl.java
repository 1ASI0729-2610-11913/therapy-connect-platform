package com.open.therapyconnect.platform.marketplace.application.internal.queryservices;

import com.open.therapyconnect.platform.marketplace.application.queryservices.CatalogQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.*;
import com.open.therapyconnect.platform.marketplace.domain.repositories.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogQueryServiceImpl implements CatalogQueryService {

    private final CatalogRepository catalogRepository;

    public CatalogQueryServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Optional<Catalog> handle(GetCatalogByIdQuery query) {
        return catalogRepository.findById(query.catalogId());
    }

    @Override
    public List<Catalog> handle(GetAllCatalogsQuery query) {
        return catalogRepository.findAll();
    }
}
