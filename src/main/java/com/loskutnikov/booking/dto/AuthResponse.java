package com.loskutnikov.booking.dto;

import com.loskutnikov.booking.model.Role;
import com.loskutnikov.booking.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response with JWT")
public record AuthResponse(

        @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiJ9...")
        String token,

        @Schema(description = "Username", example = "igor")
        String username,

        @Schema(description = "User role", example = "USER")
        Role role
) {
    public static AuthResponse of(String token, User user) {
        return new AuthResponse(token, user.getUsername(), user.getRole());
    }
}
