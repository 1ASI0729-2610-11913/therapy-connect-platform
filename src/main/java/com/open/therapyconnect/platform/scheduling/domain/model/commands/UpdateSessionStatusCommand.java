package com.open.therapyconnect.platform.scheduling.domain.model.commands;

import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.SessionStatus;
/**
 * Command to update a session status.
 *
 * @param sessionId The ID of the session.
 * @param status The new status.
 */
public record UpdateSessionStatusCommand(Long sessionId, SessionStatus status) {
    public UpdateSessionStatusCommand {
        if (sessionId == null || sessionId <= 0)
            throw new IllegalArgumentException("sessionId is required");
        if (status == null)
            throw new IllegalArgumentException("status cannot be null");
    }
}