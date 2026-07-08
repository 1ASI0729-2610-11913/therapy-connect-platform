package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.DependentCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.DependentQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.DeleteDependentCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetAllDependentsQuery;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetDependentByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateDependentResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.DependentResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.UpdateDependentResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.CreateDependentCommandFromResourceAssembler;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.DependentResourceFromEntityAssembler;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.UpdateDependentCommandFromResourceAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/dependents", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Marketplace Dependents", description = "Marketplace dependent management endpoints")
public class DependentsController {
    private final DependentCommandService dependentCommandService;
    private final DependentQueryService dependentQueryService;

    public DependentsController(DependentCommandService dependentCommandService, DependentQueryService dependentQueryService) {
        this.dependentCommandService = dependentCommandService;
        this.dependentQueryService = dependentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new marketplace dependent")
    public ResponseEntity<?>  createDependent(@RequestBody CreateDependentResource resource) {
        var command = CreateDependentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = this.dependentCommandService.handle(command)
                .flatMap(dependentId -> this.dependentQueryService.handle(new GetDependentByIdQuery(dependentId))
                        .<Result<Dependent, ApplicationError>>map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Dependent", dependentId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, DependentResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED
        );
    }

    @GetMapping("/{dependentId}")
    @Operation(summary = "Get marketplace dependent by ID")
    public ResponseEntity<DependentResource> getDependentById(@PathVariable Long dependentId) {
        var dependent = dependentQueryService.handle(new GetDependentByIdQuery(dependentId));
        if (dependent.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DependentResourceFromEntityAssembler.toResourceFromEntity(dependent.get()));
    }

    @GetMapping
    @Operation(summary = "Get all marketplace dependents")
    public ResponseEntity<List<DependentResource>> getAllDependents() {
        var dependents = dependentQueryService.handle(new GetAllDependentsQuery());
        var resources = dependents.stream()
                .map(DependentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{dependentId}")
    @Operation(summary = "Update marketplace dependent")
    public ResponseEntity<?> updateDependent(@PathVariable Long dependentId,
                                             @RequestBody UpdateDependentResource resource) {
        var command = UpdateDependentCommandFromResourceAssembler.toCommandFromResource(dependentId, resource);
        var result = dependentCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DependentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{dependentId}")
    @Operation(summary = "Delete marketplace dependent")
    public ResponseEntity<?> deleteDependent(@PathVariable Long dependentId) {
        var command = new DeleteDependentCommand(dependentId);
        var result = dependentCommandService.handle(command)
                .map(deletedId -> new MessageResource("Dependent with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
