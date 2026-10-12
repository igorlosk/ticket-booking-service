package com.loskutnikov.booking.dto;

import com.loskutnikov.booking.model.Event;
import com.loskutnikov.booking.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Event response")
public record EventResponse(
        @Schema(description = "Unique identifier", example = "1")
        Long id,

        @Schema(description = "Event name", example = "Rock Concert")
        String name,

        @Schema(description = "Event date and time", example = "2027-03-15T20:00:00")
        LocalDateTime date,

        @Schema(description = "Event venue", example = "Philharmonic Hall")
        String venue,

        @Schema(description = "Event status", example = "AVAILABLE")
        Status status,

        @Schema(description = "Event description", example = "Evening of smooth jazz")
        String description
) {
    public static EventResponse from(Event event) {
        return new EventResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getVenue(),
                event.getStatus(),
                event.getDescription()
        );
    }
}
