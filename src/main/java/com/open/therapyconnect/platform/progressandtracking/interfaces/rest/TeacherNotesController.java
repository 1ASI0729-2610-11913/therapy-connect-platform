package com.open.therapyconnect.platform.progressandtracking.interfaces.rest;

import com.open.therapyconnect.platform.progressandtracking.application.commandservices.TeacherNoteCommandService;
import com.open.therapyconnect.platform.progressandtracking.application.queryservices.TeacherNoteQueryService;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllPersonalTeacherNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllTeacherNotesQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetTeacherNoteByIdQuery;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.CreateTeacherNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.TeacherNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.UpdateTeacherNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.CreateTeacherNoteCommandFromResourceAssembler;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.TeacherNoteResourceFromEntityAssembler;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.UpdateTeacherNoteCommandFromResourceAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller that exposes teacher note endpoints.
 */
@RestController
@RequestMapping(value = "/api/v1/notes/teacher", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Teacher Notes", description = "Teacher note management endpoints")
public class TeacherNotesController {

    private final TeacherNoteCommandService teacherNoteCommandService;
    private final TeacherNoteQueryService teacherNoteQueryService;

    /**
     * Constructor
     *
     * @param teacherNoteCommandService The {@link TeacherNoteCommandService} instance
     * @param teacherNoteQueryService   The {@link TeacherNoteQueryService} instance
     */
    public TeacherNotesController(
            TeacherNoteCommandService teacherNoteCommandService,
            TeacherNoteQueryService teacherNoteQueryService) {
        this.teacherNoteCommandService = teacherNoteCommandService;
        this.teacherNoteQueryService = teacherNoteQueryService;
    }

    /**
     * Create a new teacher note
     *
     * @param resource The {@link CreateTeacherNoteResource} instance
     * @return The {@link TeacherNoteResource} resource for the created teacher note
     */
    @PostMapping
    @Operation(summary = "Create a new teacher note", description = "Creates a new note written by a teacher. Use teacherNoteType PERSONAL or INSTITUTIONAL.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Teacher note created successfully",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createTeacherNote(@RequestBody CreateTeacherNoteResource resource) {
        var createTeacherNoteCommand = CreateTeacherNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = teacherNoteCommandService.handle(createTeacherNoteCommand)
                .flatMap(noteId -> teacherNoteQueryService.handle(new GetTeacherNoteByIdQuery(noteId))
                        .<Result<com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("TeacherNote", noteId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TeacherNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    /**
     * Get teacher note by id
     *
     * @param noteId The teacher note id
     * @return The {@link TeacherNoteResource} resource for the teacher note
     */
    @GetMapping("/{noteId}")
    @Operation(summary = "Get teacher note by ID", description = "Retrieves a specific teacher note by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Teacher note found",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            ),
            @ApiResponse(responseCode = "404", description = "Teacher note not found")
    })
    public ResponseEntity<TeacherNoteResource> getTeacherNoteById(
            @PathVariable
            @Parameter(description = "Unique teacher note identifier", example = "2", required = true)
            Long noteId
    ) {
        var query = new GetTeacherNoteByIdQuery(noteId);
        var teacherNote = teacherNoteQueryService.handle(query);
        if (teacherNote.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TeacherNoteResourceFromEntityAssembler.toResourceFromEntity(teacherNote.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * Get all teacher notes
     *
     * @param authorProfileId optional filter by author profile id
     * @return The list of {@link TeacherNoteResource} resources
     */
    @GetMapping
    @Operation(
            summary = "Get all teacher notes",
            description = "Retrieves all teacher notes. Filter by authorProfileId. Optionally filter by type: personal or institutional."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Teacher notes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            )
    })
    public ResponseEntity<List<TeacherNoteResource>> getAllTeacherNotes(
            @RequestParam(required = false)
            @Parameter(description = "Filter by author profile id (IAM reference)", example = "28")
            Long authorProfileId
    ) {
        var teacherNotes = authorProfileId != null
                ? teacherNoteQueryService.handle(new GetAllTeacherNotesByAuthorProfileIdQuery(authorProfileId))
                : teacherNoteQueryService.handle(new GetAllTeacherNotesQuery());

        var resources = teacherNotes.stream()
                .map(TeacherNoteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get all personal teacher notes by author profile id
     *
     * @param authorProfileId the author profile id
     * @return The list of {@link TeacherNoteResource} resources for personal teacher notes
     */
    @GetMapping("/personal")
    @Operation(summary = "Get personal teacher notes", description = "Retrieves all PERSONAL teacher notes for a given author profile.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Personal teacher notes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            )
    })
    public ResponseEntity<List<TeacherNoteResource>> getPersonalTeacherNotes(
            @RequestParam
            @Parameter(description = "Author profile id (IAM reference)", example = "28", required = true)
            Long authorProfileId
    ) {
        var teacherNotes = teacherNoteQueryService.handle(
                new GetAllPersonalTeacherNotesByAuthorProfileIdQuery(authorProfileId));
        var resources = teacherNotes.stream()
                .map(TeacherNoteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get all institutional teacher notes by author profile id
     *
     * @param authorProfileId the author profile id
     * @return The list of {@link TeacherNoteResource} resources for institutional teacher notes
     */
    @GetMapping("/institutional")
    @Operation(summary = "Get institutional teacher notes", description = "Retrieves all INSTITUTIONAL teacher notes for a given author profile.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Institutional teacher notes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            )
    })
    public ResponseEntity<List<TeacherNoteResource>> getInstitutionalTeacherNotes(
            @RequestParam
            @Parameter(description = "Author profile id (IAM reference)", example = "28", required = true)
            Long authorProfileId
    ) {
        var teacherNotes = teacherNoteQueryService.handle(
                new GetAllInstitutionalTeacherNotesByAuthorProfileIdQuery(authorProfileId));
        var resources = teacherNotes.stream()
                .map(TeacherNoteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update teacher note
     *
     * @param noteId   The teacher note id
     * @param resource The {@link UpdateTeacherNoteResource} instance
     * @return The {@link TeacherNoteResource} resource for the updated teacher note
     */
    @PutMapping("/{noteId}")
    @Operation(summary = "Update teacher note", description = "Updates an existing teacher note.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Teacher note updated successfully",
                    content = @Content(schema = @Schema(implementation = TeacherNoteResource.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Teacher note not found")
    })
    public ResponseEntity<?> updateTeacherNote(
            @PathVariable
            @Parameter(description = "Unique teacher note identifier", example = "2", required = true)
            Long noteId,
            @RequestBody UpdateTeacherNoteResource resource
    ) {
        var updateTeacherNoteCommand = UpdateTeacherNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = teacherNoteCommandService.handle(noteId, updateTeacherNoteCommand);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TeacherNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    /**
     * Delete teacher note
     *
     * @param noteId The teacher note id
     * @return The message for the deleted teacher note
     */
    @DeleteMapping("/{noteId}")
    @Operation(summary = "Delete teacher note", description = "Deletes a teacher note by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher note deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher note not found")
    })
    public ResponseEntity<?> deleteTeacherNote(
            @PathVariable
            @Parameter(description = "Unique teacher note identifier", example = "2", required = true)
            Long noteId
    ) {
        var deleteNoteCommand = new DeleteNoteCommand(noteId);
        var result = teacherNoteCommandService.handle(deleteNoteCommand)
                .map(_ -> new MessageResource("Teacher note with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
