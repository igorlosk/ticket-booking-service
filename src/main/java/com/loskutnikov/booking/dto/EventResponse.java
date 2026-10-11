package com.loskutnikov.booking.dto;

import com.loskutnikov.booking.model.Event;
import com.loskutnikov.booking.model.Status;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String name,
        LocalDateTime date,
        String venue,
        Status status,
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
