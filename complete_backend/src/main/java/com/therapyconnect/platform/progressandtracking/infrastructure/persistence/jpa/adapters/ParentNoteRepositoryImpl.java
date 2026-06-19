package com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.adapters;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.therapyconnect.platform.progressandtracking.domain.repositories.ParentNoteRepository;
import com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.assemblers.ParentNotePersistenceAssembler;
import com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.repositories.ParentNotePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository adapter that bridges the ParentNote domain repository port with Spring Data JPA.
 */
@Repository
public class ParentNoteRepositoryImpl implements ParentNoteRepository {

    private final ParentNotePersistenceRepository parentNotePersistenceRepository;

    public ParentNoteRepositoryImpl(ParentNotePersistenceRepository parentNotePersistenceRepository) {
        this.parentNotePersistenceRepository = parentNotePersistenceRepository;
    }

    @Override
    public Optional<ParentNote> findById(Long id) {
        return parentNotePersistenceRepository.findById(id)
                .map(ParentNotePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<ParentNote> findAll() {
        return parentNotePersistenceRepository.findAll().stream()
                .map(ParentNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<ParentNote> findAllByAuthorProfileId(Long authorProfileId) {
        return parentNotePersistenceRepository.findAllByAuthorProfileId(authorProfileId).stream()
                .map(ParentNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public ParentNote save(ParentNote parentNote) {
        var saved = parentNotePersistenceRepository.save(
                ParentNotePersistenceAssembler.toPersistenceFromDomain(parentNote));
        return ParentNotePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return parentNotePersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        parentNotePersistenceRepository.deleteById(id);
    }
}
