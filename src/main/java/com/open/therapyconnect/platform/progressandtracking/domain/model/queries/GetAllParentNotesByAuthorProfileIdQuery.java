package com.open.therapyconnect.platform.progressandtracking.domain.model.queries;

/**
 * Query to retrieve all parent notes for a given author profile.
 *
 * @param authorProfileId the profile id of the parent author
 */
public record GetAllParentNotesByAuthorProfileIdQuery(Long authorProfileId) {
}
