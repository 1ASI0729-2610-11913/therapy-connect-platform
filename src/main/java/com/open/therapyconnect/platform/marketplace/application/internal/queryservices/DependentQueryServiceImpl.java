package com.open.therapyconnect.platform.marketplace.application.internal.queryservices;

import com.open.therapyconnect.platform.marketplace.application.queryservices.DependentQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetDependentByIdQuery;
import com.open.therapyconnect.platform.marketplace.domain.repositories.DependentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DependentQueryServiceImpl implements DependentQueryService {
    private final DependentRepository dependentRepository;
    public DependentQueryServiceImpl(DependentRepository dependentRepository) {
        this.dependentRepository = dependentRepository;
    }

    @Override
    public Optional<Dependent> handle(GetDependentByIdQuery query) {
        return this.dependentRepository.findById(query.dependentId());
    }
}
