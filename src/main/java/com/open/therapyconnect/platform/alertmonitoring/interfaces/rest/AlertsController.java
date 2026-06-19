package com.open.therapyconnect.platform.alertmonitoring.interfaces.rest;

import com.open.therapyconnect.platform.alertmonitoring.application.queryservices.AlertQueryService;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.queries.GetAlertsByStatusQuery;
import com.open.therapyconnect.platform.alertmonitoring.interfaces.rest.resources.AlertResource;
import com.open.therapyconnect.platform.alertmonitoring.interfaces.rest.transform.AlertResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/alerts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Alerts", description = "Alert monitoring endpoints")
public class AlertsController {

    private final AlertQueryService queryService;

    public AlertsController(AlertQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get alerts by status ordered by criticality descending (HIGH > MEDIUM > LOW)")
    public ResponseEntity<List<AlertResource>> getAlertsByStatus(@RequestParam String status) {
        var alerts = queryService.handle(new GetAlertsByStatusQuery(status));
        var resources = alerts.stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
