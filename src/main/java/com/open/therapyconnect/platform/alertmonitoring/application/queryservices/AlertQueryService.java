package com.open.therapyconnect.platform.alertmonitoring.application.queryservices;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.queries.GetAlertsByStatusQuery;

import java.util.List;

public interface AlertQueryService {
    List<Alert> handle(GetAlertsByStatusQuery query);
}
