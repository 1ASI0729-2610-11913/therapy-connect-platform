package com.open.therapyconnect.platform.scheduling.domain.model.valueobjects;

public enum ReminderStatus {
    PENDING,        // recordatorio activo, aún no enviado
    SENT,           // recordatorio enviado al padre/teacher
    ACKNOWLEDGED,   // el destinatario lo vio/confirmó
    CANCELLED       // recordatorio cancelado
}