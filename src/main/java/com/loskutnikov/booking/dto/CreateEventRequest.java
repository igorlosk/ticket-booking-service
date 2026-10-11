package com.loskutnikov.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateEventRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Date must not be null")
        @Future(message = "Date must be in the future")
        LocalDateTime date,

        @NotBlank(message = "Venue must not be blank")
        String venue,

        String description


) {
}
