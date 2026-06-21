package com.open.therapyconnect.platform.coursesandlearning.interfaces.rest;

import com.open.therapyconnect.platform.coursesandlearning.application.commandservices.EvaluationCommandService;
import com.open.therapyconnect.platform.coursesandlearning.application.queryservices.EvaluationQueryService;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.commands.DeleteEvaluationCommand;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetAllEvaluationsQuery;
import com.open.therapyconnect.platform.coursesandlearning.domain.model.queries.GetEvaluationByIdQuery;
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
@RequestMapping(value = "/api/v1/evaluations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Evaluations", description = "Evaluation management endpoints")
public class EvaluationsController {

    private final EvaluationCommandService evaluationCommandService;
    private final EvaluationQueryService evaluationQueryService;

    public EvaluationsController(EvaluationCommandService evaluationCommandService,
                                 EvaluationQueryService evaluationQueryService) {
        this.evaluationCommandService = evaluationCommandService;
        this.evaluationQueryService = evaluationQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new evaluation")
    public ResponseEntity<?> createEvaluation(@RequestBody CreateEvaluationResource resource) {
        var command = CreateEvaluationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = evaluationCommandService.handle(command)
                .flatMap(evaluationId -> evaluationQueryService.handle(new GetEvaluationByIdQuery(evaluationId))
                        .<Result<com.open.therapyconnect.platform.coursesandlearning.domain.model.aggregates.Evaluation, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Evaluation", evaluationId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                EvaluationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{evaluationId}")
    @Operation(summary = "Get evaluation by ID")
    public ResponseEntity<EvaluationResource> getEvaluationById(@PathVariable Long evaluationId) {
        var query = new GetEvaluationByIdQuery(evaluationId);
        var evaluation = evaluationQueryService.handle(query);
        if (evaluation.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EvaluationResourceFromEntityAssembler.toResourceFromEntity(evaluation.get()));
    }

    @GetMapping
    @Operation(summary = "Get all evaluations")
    public ResponseEntity<List<EvaluationResource>> getAllEvaluations() {
        var evaluations = evaluationQueryService.handle(new GetAllEvaluationsQuery());
        var resources = evaluations.stream()
                .map(EvaluationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{evaluationId}")
    @Operation(summary = "Update evaluation")
    public ResponseEntity<?> updateEvaluation(@PathVariable Long evaluationId,
                                              @RequestBody UpdateEvaluationResource resource) {
        var command = UpdateEvaluationCommandFromResourceAssembler.toCommandFromResource(evaluationId, resource);
        var result = evaluationCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                EvaluationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{evaluationId}")
    @Operation(summary = "Delete evaluation")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long evaluationId) {
        var command = new DeleteEvaluationCommand(evaluationId);
        var result = evaluationCommandService.handle(command)
                .map(deletedId -> new MessageResource("Evaluation with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
