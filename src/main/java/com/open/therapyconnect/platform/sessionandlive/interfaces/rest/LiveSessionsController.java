package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.LiveSessionCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.LiveSessionQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteLiveSessionCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateLiveSessionStatusCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.LiveSessionStatus;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform.*;
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
@RequestMapping(value = "/api/v1/live-sessions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Live Sessions", description = "Live, private, and group session management endpoints")
public class LiveSessionsController {

    private final LiveSessionCommandService liveSessionCommandService;
    private final LiveSessionQueryService liveSessionQueryService;

    public LiveSessionsController(LiveSessionCommandService liveSessionCommandService,
                                   LiveSessionQueryService liveSessionQueryService) {
        this.liveSessionCommandService = liveSessionCommandService;
        this.liveSessionQueryService = liveSessionQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new live session")
    public ResponseEntity<?> createLiveSession(@RequestBody CreateLiveSessionResource resource) {
        var command = CreateLiveSessionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = liveSessionCommandService.handle(command)
                .flatMap(id -> liveSessionQueryService.handle(new GetLiveSessionByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("LiveSession", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                LiveSessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{liveSessionId}")
    @Operation(summary = "Get live session by ID")
    public ResponseEntity<LiveSessionResource> getLiveSessionById(@PathVariable Long liveSessionId) {
        var session = liveSessionQueryService.handle(new GetLiveSessionByIdQuery(liveSessionId));
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(LiveSessionResourceFromEntityAssembler.toResourceFromEntity(session.get()));
    }

    @GetMapping
    @Operation(summary = "Get all live sessions, optionally filtered by mode (GROUP, PRIVATE, LIVE)")
    public ResponseEntity<List<LiveSessionResource>> getAllLiveSessions(
            @RequestParam(required = false) String mode) {
        List<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession> sessions;
        if (mode != null && !mode.isBlank()) {
            sessions = liveSessionQueryService.handle(new GetLiveSessionsByModeQuery(mode));
        } else {
            sessions = liveSessionQueryService.handle(new GetAllLiveSessionsQuery());
        }
        var resources = sessions.stream()
                .map(LiveSessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Get live sessions by teacher ID")
    public ResponseEntity<List<LiveSessionResource>> getLiveSessionsByTeacherId(@PathVariable Long teacherId) {
        var sessions = liveSessionQueryService.handle(new GetLiveSessionsByTeacherIdQuery(teacherId));
        var resources = sessions.stream()
                .map(LiveSessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get live sessions by student ID")
    public ResponseEntity<List<LiveSessionResource>> getLiveSessionsByStudentId(@PathVariable Long studentId) {
        var sessions = liveSessionQueryService.handle(new GetLiveSessionsByStudentIdQuery(studentId));
        var resources = sessions.stream()
                .map(LiveSessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{liveSessionId}")
    @Operation(summary = "Update live session")
    public ResponseEntity<?> updateLiveSession(@PathVariable Long liveSessionId,
                                                @RequestBody UpdateLiveSessionResource resource) {
        var command = UpdateLiveSessionCommandFromResourceAssembler.toCommandFromResource(liveSessionId, resource);
        var result = liveSessionCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                LiveSessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{liveSessionId}/status")
    @Operation(summary = "Update live session status")
    public ResponseEntity<?> updateLiveSessionStatus(@PathVariable Long liveSessionId,
                                                      @RequestBody UpdateLiveSessionStatusResource resource) {
        var command = new UpdateLiveSessionStatusCommand(
                liveSessionId,
                LiveSessionStatus.valueOf(resource.status().toUpperCase())
        );
        var result = liveSessionCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                LiveSessionResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{liveSessionId}")
    @Operation(summary = "Delete live session")
    public ResponseEntity<?> deleteLiveSession(@PathVariable Long liveSessionId) {
        var command = new DeleteLiveSessionCommand(liveSessionId);
        var result = liveSessionCommandService.handle(command)
                .map(id -> new MessageResource("Live session with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
