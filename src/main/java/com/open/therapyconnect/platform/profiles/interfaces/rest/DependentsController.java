package com.open.therapyconnect.platform.profiles.interfaces.rest;

import com.open.therapyconnect.platform.profiles.application.commandservices.DependentCommandService;
import com.open.therapyconnect.platform.profiles.application.queryservices.DependentQueryService;
import com.open.therapyconnect.platform.profiles.domain.model.commands.DeleteDependentCommand;
import com.open.therapyconnect.platform.profiles.domain.model.queries.GetAllDependentsQuery;
import com.open.therapyconnect.platform.profiles.domain.model.queries.GetDependentByIdQuery;
import com.open.therapyconnect.platform.profiles.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.profiles.interfaces.rest.transform.*;
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
@Tag(name = "Dependents", description = "Dependent management endpoints")
public class DependentsController {

    private final DependentCommandService dependentCommandService;
    private final DependentQueryService dependentQueryService;

    public DependentsController(DependentCommandService dependentCommandService,
                                DependentQueryService dependentQueryService) {
        this.dependentCommandService = dependentCommandService;
        this.dependentQueryService = dependentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new dependent")
    public ResponseEntity<?> createDependent(@RequestBody CreateDependentResource resource) {
        var command = CreateDependentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = dependentCommandService.handle(command)
                .flatMap(dependentId -> dependentQueryService.handle(new GetDependentByIdQuery(dependentId))
                        .<Result<com.open.therapyconnect.platform.profiles.domain.model.aggregates.Dependent, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Dependent", dependentId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DependentResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{dependentId}")
    @Operation(summary = "Get dependent by ID")
    public ResponseEntity<DependentResource> getDependentById(@PathVariable Long dependentId) {
        var query = new GetDependentByIdQuery(dependentId);
        var dependent = dependentQueryService.handle(query);
        if (dependent.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DependentResourceFromEntityAssembler.toResourceFromEntity(dependent.get()));
    }

    @GetMapping
    @Operation(summary = "Get all dependents")
    public ResponseEntity<List<DependentResource>> getAllDependents() {
        var dependents = dependentQueryService.handle(new GetAllDependentsQuery());
        var resources = dependents.stream()
                .map(DependentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{dependentId}")
    @Operation(summary = "Update dependent")
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
    @Operation(summary = "Delete dependent")
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
