package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.CatalogCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.CatalogQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.commands.DeleteCatalogCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetAllCatalogsQuery;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetCatalogByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.*;
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
@RequestMapping(value = "/api/v1/catalogs", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Catalogs", description = "Catalog management endpoints")
public class CatalogsController {

    private final CatalogCommandService catalogCommandService;
    private final CatalogQueryService catalogQueryService;

    public CatalogsController(CatalogCommandService catalogCommandService, CatalogQueryService catalogQueryService) {
        this.catalogCommandService = catalogCommandService;
        this.catalogQueryService = catalogQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new catalog")
    public ResponseEntity<?> createCatalog(@RequestBody CreateCatalogResource resource) {
        var command = CreateCatalogCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = catalogCommandService.handle(command)
                .flatMap(catalogId -> catalogQueryService.handle(new GetCatalogByIdQuery(catalogId))
                        .<Result<com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Catalog", catalogId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                CatalogResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{catalogId}")
    @Operation(summary = "Get catalog by ID")
    public ResponseEntity<CatalogResource> getCatalogById(@PathVariable Long catalogId) {
        var query = new GetCatalogByIdQuery(catalogId);
        var catalog = catalogQueryService.handle(query);
        if (catalog.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CatalogResourceFromEntityAssembler.toResourceFromEntity(catalog.get()));
    }

    @GetMapping
    @Operation(summary = "Get all catalogs")
    public ResponseEntity<List<CatalogResource>> getAllCatalogs() {
        var catalogs = catalogQueryService.handle(new GetAllCatalogsQuery());
        var resources = catalogs.stream()
                .map(CatalogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{catalogId}")
    @Operation(summary = "Update catalog")
    public ResponseEntity<?> updateCatalog(@PathVariable Long catalogId, @RequestBody UpdateCatalogResource resource) {
        var command = UpdateCatalogCommandFromResourceAssembler.toCommandFromResource(catalogId, resource);
        var result = catalogCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                CatalogResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{catalogId}")
    @Operation(summary = "Delete catalog")
    public ResponseEntity<?> deleteCatalog(@PathVariable Long catalogId) {
        var command = new DeleteCatalogCommand(catalogId);
        var result = catalogCommandService.handle(command)
                .map(deletedId -> new MessageResource("Catalog with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
