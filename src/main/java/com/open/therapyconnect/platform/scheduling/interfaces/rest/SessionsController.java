package com.open.therapyconnect.platform.scheduling.interfaces.rest;

import com.open.therapyconnect.platform.scheduling.domain.model.valueobjects.SessionStatus;
import com.open.therapyconnect.platform.scheduling.application.commandservices.SessionCommandService;
import com.open.therapyconnect.platform.scheduling.application.queryservices.SessionQueryService;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.DeleteSessionCommand;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.UpdateSessionStatusCommand;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.GetAllSessionsQuery;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.GetSessionByIdQuery;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.transform.*;
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

/**
 * REST controller that exposes session endpoints.
 */
@RestController
@RequestMapping(value = "/api/v1/sessions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Sessions", description = "Session management endpoints")
public class SessionsController {

    private final SessionCommandService sessionCommandService;
    private final SessionQueryService sessionQueryService;

    public SessionsController(SessionCommandService sessionCommandService, SessionQueryService sessionQueryService) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new session")
    public ResponseEntity<?> createSession(@RequestBody CreateSessionResource resource) {
        var command = CreateSessionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = sessionCommandService.handle(command)
                .flatMap(sessionId -> sessionQueryService.handle(new GetSessionByIdQuery(sessionId))
                        .<Result<com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Session", sessionId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{sessionId}")
    @Operation(summary = "Get session by ID")
    public ResponseEntity<SessionResource> getSessionById(@PathVariable Long sessionId) {
        var query = new GetSessionByIdQuery(sessionId);
        var session = sessionQueryService.handle(query);
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SessionResourceFromEntityAssembler.toResourceFromEntity(session.get()));
    }

    @GetMapping
    @Operation(summary = "Get all sessions")
    public ResponseEntity<List<SessionResource>> getAllSessions() {
        var sessions = sessionQueryService.handle(new GetAllSessionsQuery());
        var resources = sessions.stream()
                .map(SessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{sessionId}")
    @Operation(summary = "Update session")
    public ResponseEntity<?> updateSession(@PathVariable Long sessionId, @RequestBody UpdateSessionResource resource) {
        var command = UpdateSessionCommandFromResourceAssembler.toCommandFromResource(sessionId, resource);
        var result = sessionCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{sessionId}/status")
    @Operation(summary = "Update session status")
    public ResponseEntity<?> updateSessionStatus(@PathVariable Long sessionId, @RequestBody UpdateSessionStatusResource resource) {
        var command = new UpdateSessionStatusCommand(sessionId, SessionStatus.valueOf(resource.status().toUpperCase()));
        var result = sessionCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{sessionId}")
    @Operation(summary = "Delete session")
    public ResponseEntity<?> deleteSession(@PathVariable Long sessionId) {
        var command = new DeleteSessionCommand(sessionId);
        var result = sessionCommandService.handle(command)
                .map(deletedId -> new MessageResource("Session with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}