package com.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionIdTest {

    @Test
    void sessionIdWithValidValueCreatesSuccessfully() {
        var sessionId = new SessionId(100L);
        assertEquals(100L, sessionId.id());
    }

    @Test
    void sessionIdWithNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SessionId(null));
    }

    @Test
    void sessionIdWithZeroThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SessionId(0L));
    }
}
