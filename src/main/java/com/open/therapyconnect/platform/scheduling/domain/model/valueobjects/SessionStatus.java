package com.open.therapyconnect.platform.scheduling.domain.model.valueobjects;

public enum SessionStatus {
    PENDING,        // sesión agendada, sin confirmar
    CONFIRMED,      // confirmada por el terapeuta/teacher
    IN_PROGRESS,    // sesión en curso
    COMPLETED,      // sesión finalizada
    CANCELLED       // cancelada por cualquiera de las partes
}