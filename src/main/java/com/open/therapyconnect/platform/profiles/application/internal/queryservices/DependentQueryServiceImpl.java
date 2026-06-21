package com.open.therapyconnect.platform.profiles.application.internal.queryservices;

import com.open.therapyconnect.platform.profiles.application.queryservices.DependentQueryService;
import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.domain.model.queries.*;
import com.open.therapyconnect.platform.profiles.domain.repositories.DependentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentQueryServiceImpl implements DependentQueryService {

    private final DependentRepository dependentRepository;

    public DependentQueryServiceImpl(DependentRepository dependentRepository) {
        this.dependentRepository = dependentRepository;
    }

    @Override
    public Optional<Dependent> handle(GetDependentByIdQuery query) {
        return dependentRepository.findById(query.dependentId());
    }

    @Override
    public List<Dependent> handle(GetAllDependentsQuery query) {
        return dependentRepository.findAll();
    }
}
