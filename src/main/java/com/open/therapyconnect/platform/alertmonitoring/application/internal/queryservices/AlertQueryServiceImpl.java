package com.open.therapyconnect.platform.alertmonitoring.application.internal.queryservices;

import com.open.therapyconnect.platform.alertmonitoring.application.queryservices.AlertQueryService;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates.Alert;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.queries.GetAlertsByStatusQuery;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertCriticality;
import com.open.therapyconnect.platform.alertmonitoring.domain.repositories.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class AlertQueryServiceImpl implements AlertQueryService {

    private static final Map<AlertCriticality, Integer> CRITICALITY_ORDER = Map.of(
            AlertCriticality.HIGH, 0,
            AlertCriticality.MEDIUM, 1,
            AlertCriticality.LOW, 2
    );

    private final AlertRepository alertRepository;

    public AlertQueryServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public List<Alert> handle(GetAlertsByStatusQuery query) {
        return alertRepository.findByStatus(query.status())
                .stream()
                .sorted(Comparator.comparingInt(alert -> CRITICALITY_ORDER.get(alert.getCriticality())))
                .toList();
    }
}
