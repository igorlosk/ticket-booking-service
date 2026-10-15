package com.loskutnikov.booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Registration request")
public record RegisterRequest(

        @NotBlank(message = "Username must not be blank")
        @Size(min = 3, max = 100, message = "Username must be 3-100 characters")
        @Schema(description = "Username", example = "igor")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, max = 100, message = "Password must be 8-100 characters")
        @Schema(description = "Password", example = "secret123")
        String password
) {
}
