package com.loskutnikov.booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request")
public record LoginRequest(

        @NotBlank(message = "Username must not be blank")
        @Schema(description = "Username", example = "igor")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Schema(description = "Password", example = "secret123")
        String password
) {
}
