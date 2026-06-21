package com.open.therapyconnect.platform.coursesandlearning.application.queryservices;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface RegistrationQueryService {
    Optional<Registration> handle(GetRegistrationByIdQuery query);
    List<Registration> handle(GetAllRegistrationsQuery query);
}
