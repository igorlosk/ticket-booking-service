package com.loskutnikov.booking.dto;

import com.loskutnikov.booking.model.Role;
import com.loskutnikov.booking.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response")
public record UserResponse(

        @Schema(description = "User id", example = "1")
        Long id,

        @Schema(description = "Username", example = "igor")
        String username,

        @Schema(description = "User role", example = "USER")
        Role role
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole());
    }
}
