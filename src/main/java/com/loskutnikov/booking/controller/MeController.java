package com.loskutnikov.booking.controller;

import com.loskutnikov.booking.dto.UserResponse;
import com.loskutnikov.booking.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
@Tag(name = "User", description = "Current user info")
@SecurityRequirement(name = "bearerAuth")
public class MeController {

    @GetMapping
    @Operation(summary = "Get current authenticated user")
    public UserResponse me(@AuthenticationPrincipal User user) {
        return UserResponse.from(user);
    }
}
