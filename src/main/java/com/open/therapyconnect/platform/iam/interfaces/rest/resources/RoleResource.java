package com.open.therapyconnect.platform.iam.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Resource that represents an IAM role exposed by the REST API.
 */
@Schema(
    name = "RoleResponse",
    description = "Role information response",
    example = "{\"id\": 1, \"name\": \"ROLE_PARENT_PERSONAL\"}"
)
public record RoleResource(
    @Schema(description = "Role unique identifier", example = "1")
    Long id,

    @Schema(description = "Role name", example = "ROLE_PARENT_PERSONAL", allowableValues = {"ROLE_PARENT_PERSONAL", "ROLE_PARENT_INSTITUTIONAL", "ROLE_TEACHER_PERSONAL", "ROLE_TEACHER_INSTITUTIONAL", "ROLE_INSTITUTION_ADMIN"})
    String name
) {
}
