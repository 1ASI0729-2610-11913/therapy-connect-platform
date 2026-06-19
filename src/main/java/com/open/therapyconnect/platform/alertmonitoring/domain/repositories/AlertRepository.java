package com.open.therapyconnect.platform.alertmonitoring.domain.repositories;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;

import java.util.List;

public interface AlertRepository {
    List<Alert> findByStatus(String status);
}
