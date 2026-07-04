package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionMode;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.LiveSessionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveSessionPersistenceRepository extends JpaRepository<LiveSessionPersistenceEntity, Long> {
    List<LiveSessionPersistenceEntity> findByTeacherId(Long teacherId);
    List<LiveSessionPersistenceEntity> findByStudentId(Long studentId);
    List<LiveSessionPersistenceEntity> findBySessionMode(SessionMode sessionMode);
}
