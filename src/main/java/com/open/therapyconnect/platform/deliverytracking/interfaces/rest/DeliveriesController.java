package com.open.therapyconnect.platform.deliverytracking.interfaces.rest;

import com.open.therapyconnect.platform.deliverytracking.application.queryservices.DeliveryQueryService;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveriesByDateAndStatusQuery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveryByIdQuery;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources.DeliveryDetailResource;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.resources.DeliverySummaryResource;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.transform.DeliveryDetailResourceFromEntityAssembler;
import com.open.therapyconnect.platform.deliverytracking.interfaces.rest.transform.DeliverySummaryResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/deliveries", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deliveries", description = "Delivery tracking endpoints")
public class DeliveriesController {

    private final DeliveryQueryService queryService;

    public DeliveriesController(DeliveryQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get deliveries by date and status")
    public ResponseEntity<List<DeliverySummaryResource>> getDeliveriesByDateAndStatus(
            @RequestParam String date,
            @RequestParam String status) {
        var resolvedDate = "today".equalsIgnoreCase(date) ? LocalDate.now().toString() : date;
        var deliveries = queryService.handle(new GetDeliveriesByDateAndStatusQuery(resolvedDate, status));
        var resources = deliveries.stream()
                .map(DeliverySummaryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get delivery detail by ID")
    public ResponseEntity<DeliveryDetailResource> getDeliveryById(@PathVariable Long id) {
        var delivery = queryService.handle(new GetDeliveryByIdQuery(id));
        if (delivery.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DeliveryDetailResourceFromEntityAssembler.toResourceFromEntity(delivery.get()));
    }
}
