package com.therapyconnect.platform.progressandtracking.application.queryservices;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllPersonalTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesByAuthorProfileIdQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesQuery;
import com.therapyconnect.platform.progressandtracking.domain.model.queries.GetTeacherNoteByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Application service contract for teacher note read queries.
 */
public interface TeacherNoteQueryService {

    /**
     * Handles retrieval of a teacher note by id.
     *
     * @param query note-id query
     * @return matching teacher note, if found
     * @see GetTeacherNoteByIdQuery
     */
    Optional<TeacherNote> handle(GetTeacherNoteByIdQuery query);

    /**
     * Handles retrieval of all teacher notes.
     *
     * @param query query marker
     * @return list of all teacher notes
     * @see GetAllTeacherNotesQuery
     */
    List<TeacherNote> handle(GetAllTeacherNotesQuery query);

    /**
     * Handles retrieval of all teacher notes for a given author profile.
     *
     * @param query query containing the author profile id
     * @return list of teacher notes for the given author
     * @see GetAllTeacherNotesByAuthorProfileIdQuery
     */
    List<TeacherNote> handle(GetAllTeacherNotesByAuthorProfileIdQuery query);

    /**
     * Handles retrieval of all PERSONAL teacher notes for a given author profile.
     *
     * @param query query containing the author profile id
     * @return list of personal teacher notes for the given author
     * @see GetAllPersonalTeacherNotesByAuthorProfileIdQuery
     */
    List<TeacherNote> handle(GetAllPersonalTeacherNotesByAuthorProfileIdQuery query);

    /**
     * Handles retrieval of all INSTITUTIONAL teacher notes for a given author profile.
     *
     * @param query query containing the author profile id
     * @return list of institutional teacher notes for the given author
     * @see GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery
     */
    List<TeacherNote> handle(GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery query);
}
