package com.open.therapyconnect.platform.profiles.application.queryservices;

import com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface DependentQueryService {
    Optional<Dependent> handle(GetDependentByIdQuery query);
    List<Dependent> handle(GetAllDependentsQuery query);
}
