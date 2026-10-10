package com.loskutnikov.booking.dto;

import com.loskutnikov.booking.model.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateEventRequest(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Date must not be null")
        @Future(message = "Date must be in the future")
        LocalDateTime date,

        @NotBlank(message = "Venue must not be blank")
        String venue,

        @NotNull(message = "Status must not be null")
        Status status
) {
}
