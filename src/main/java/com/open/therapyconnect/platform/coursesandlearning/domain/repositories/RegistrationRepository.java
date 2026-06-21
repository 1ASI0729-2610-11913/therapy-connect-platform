package com.open.therapyconnect.platform.coursesandlearning.domain.repositories;

import com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration;

import java.util.List;
import java.util.Optional;

/** Registration repository port. */
public interface RegistrationRepository {
    Optional<Registration> findById(Long id);
    List<Registration> findAll();
    Registration save(Registration registration);
    boolean existsById(Long id);
    void deleteById(Long id);
}
