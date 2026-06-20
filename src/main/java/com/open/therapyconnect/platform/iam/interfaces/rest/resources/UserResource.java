package com.open.therapyconnect.platform.iam.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * Resource representing an IAM user returned by the REST API.
 */
@Schema(
    name = "UserResponse",
    description = "User information response",
    example = "{\"id\": 1, \"username\": \"john.doe\", \"roles\": [\"ROLE_PARENT_PERSONAL\"]}"
)
public record UserResource(
    @Schema(description = "User unique identifier", example = "1")
    Long id,

    @Schema(description = "User username", example = "john.doe")
    String username,

    @Schema(description = "User assigned roles", example = "[\"ROLE_PARENT_PERSONAL\"]")
    List<String> roles
) {
}
