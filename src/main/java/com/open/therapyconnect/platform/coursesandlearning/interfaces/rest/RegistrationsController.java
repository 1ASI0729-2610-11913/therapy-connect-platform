package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.RegistrationCommandService;
import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.RegistrationQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.DeleteRegistrationCommand;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetAllRegistrationsQuery;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetRegistrationByIdQuery;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.coursesandlearning.interfaces.rest.transform.*;
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
@RequestMapping(value = "/api/v1/registrations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Registrations", description = "Registration management endpoints")
public class RegistrationsController {

    private final RegistrationCommandService registrationCommandService;
    private final RegistrationQueryService registrationQueryService;

    public RegistrationsController(RegistrationCommandService registrationCommandService,
                                   RegistrationQueryService registrationQueryService) {
        this.registrationCommandService = registrationCommandService;
        this.registrationQueryService = registrationQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new registration")
    public ResponseEntity<?> createRegistration(@RequestBody CreateRegistrationResource resource) {
        var command = CreateRegistrationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = registrationCommandService.handle(command)
                .flatMap(registrationId -> registrationQueryService.handle(new GetRegistrationByIdQuery(registrationId))
                        .<Result<com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Registration, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Registration", registrationId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                RegistrationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{registrationId}")
    @Operation(summary = "Get registration by ID")
    public ResponseEntity<RegistrationResource> getRegistrationById(@PathVariable Long registrationId) {
        var query = new GetRegistrationByIdQuery(registrationId);
        var registration = registrationQueryService.handle(query);
        if (registration.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(RegistrationResourceFromEntityAssembler.toResourceFromEntity(registration.get()));
    }

    @GetMapping
    @Operation(summary = "Get all registrations")
    public ResponseEntity<List<RegistrationResource>> getAllRegistrations() {
        var registrations = registrationQueryService.handle(new GetAllRegistrationsQuery());
        var resources = registrations.stream()
                .map(RegistrationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{registrationId}")
    @Operation(summary = "Update registration")
    public ResponseEntity<?> updateRegistration(@PathVariable Long registrationId,
                                                @RequestBody UpdateRegistrationResource resource) {
        var command = UpdateRegistrationCommandFromResourceAssembler.toCommandFromResource(registrationId, resource);
        var result = registrationCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                RegistrationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{registrationId}")
    @Operation(summary = "Delete registration")
    public ResponseEntity<?> deleteRegistration(@PathVariable Long registrationId) {
        var command = new DeleteRegistrationCommand(registrationId);
        var result = registrationCommandService.handle(command)
                .map(deletedId -> new MessageResource("Registration with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
