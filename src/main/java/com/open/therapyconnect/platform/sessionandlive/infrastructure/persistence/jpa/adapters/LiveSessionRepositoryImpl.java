package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionMode;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.LiveSessionRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.LiveSessionPersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.LiveSessionPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LiveSessionRepositoryImpl implements LiveSessionRepository {

    private final LiveSessionPersistenceRepository persistenceRepository;

    public LiveSessionRepositoryImpl(LiveSessionPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<LiveSession> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(LiveSessionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<LiveSession> findAll() {
        return persistenceRepository.findAll().stream()
                .map(LiveSessionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<LiveSession> findByTeacherId(Long teacherId) {
        return persistenceRepository.findByTeacherId(teacherId).stream()
                .map(LiveSessionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<LiveSession> findByStudentId(Long studentId) {
        return persistenceRepository.findByStudentId(studentId).stream()
                .map(LiveSessionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<LiveSession> findBySessionMode(SessionMode sessionMode) {
        return persistenceRepository.findBySessionMode(sessionMode).stream()
                .map(LiveSessionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public LiveSession save(LiveSession liveSession) {
        var saved = persistenceRepository.save(LiveSessionPersistenceAssembler.toPersistenceFromDomain(liveSession));
        return LiveSessionPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return persistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        persistenceRepository.deleteById(id);
    }
}
