package com.therapyconnect.platform.progressandtracking.application.internal.queryservices;

import com.therapyconnect.platform.progressandtracking.application.queryservices.TeacherNoteQueryService;
import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllPersonalTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetTeacherNoteByIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import com.therapyconnect.platform.progressandtracking.domain.repositories.TeacherNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application service that executes teacher note queries.
 */
@Service
public class TeacherNoteQueryServiceImpl implements TeacherNoteQueryService {

    private final TeacherNoteRepository teacherNoteRepository;

    public TeacherNoteQueryServiceImpl(TeacherNoteRepository teacherNoteRepository) {
        this.teacherNoteRepository = teacherNoteRepository;
    }

    @Override
    public Optional<TeacherNote> handle(GetTeacherNoteByIdQuery query) {
        return teacherNoteRepository.findById(query.noteId());
    }

    @Override
    public List<TeacherNote> handle(GetAllTeacherNotesQuery query) {
        return teacherNoteRepository.findAll();
    }

    @Override
    public List<TeacherNote> handle(GetAllTeacherNotesByAuthorProfileIdQuery query) {
        return teacherNoteRepository.findAllByAuthorProfileId(query.authorProfileId());
    }

    @Override
    public List<TeacherNote> handle(GetAllPersonalTeacherNotesByAuthorProfileIdQuery query) {
        return teacherNoteRepository.findAllByAuthorProfileIdAndTeacherNoteType(
                query.authorProfileId(), TeacherNoteType.PERSONAL);
    }

    @Override
    public List<TeacherNote> handle(GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery query) {
        return teacherNoteRepository.findAllByAuthorProfileIdAndTeacherNoteType(
                query.authorProfileId(), TeacherNoteType.INSTITUTIONAL);
    }
}
