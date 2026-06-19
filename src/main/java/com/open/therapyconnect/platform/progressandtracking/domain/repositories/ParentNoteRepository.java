package com.open.therapyconnect.platform.progressandtracking.domain.repositories;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;

import java.util.List;
import java.util.Optional;

/**
 * Progress-and-tracking parent note repository port.
 */
public interface ParentNoteRepository {

    Optional<ParentNote> findById(Long id);

    List<ParentNote> findAll();

    List<ParentNote> findAllByAuthorProfileId(Long authorProfileId);

    ParentNote save(ParentNote parentNote);

    boolean existsById(Long id);

    void deleteById(Long id);
}
