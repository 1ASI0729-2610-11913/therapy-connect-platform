package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities.TeacherNotePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for teacher note persistence entities.
 */
@Repository
public interface TeacherNotePersistenceRepository extends JpaRepository<TeacherNotePersistenceEntity, Long> {

    List<TeacherNotePersistenceEntity> findAllByAuthorProfileId(Long authorProfileId);

    List<TeacherNotePersistenceEntity> findAllByAuthorProfileIdAndTeacherNoteType(
            Long authorProfileId, TeacherNoteType teacherNoteType);
}
