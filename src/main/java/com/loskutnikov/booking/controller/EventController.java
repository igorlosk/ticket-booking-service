package com.loskutnikov.booking.controller;

import com.loskutnikov.booking.dto.*;
import com.loskutnikov.booking.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Events", description = "Operations for managing events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new event",
            description = "Creates a new event with status AVAILABLE"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<EventResponse> create(@Valid @RequestBody CreateEventRequest request) {
        EventResponse created = eventService.create(request);
        URI location = URI.create("/api/v1/events/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    @Operation(
            summary = "Get all events",
            description = "Returns paginated list of all events"
    )
    @ApiResponse(responseCode = "200", description = "List of events")
    public Page<EventResponse> findAll(Pageable pageable) {
        return eventService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public EventResponse findById(
            @Parameter(description = "Event id", example = "1")
            @PathVariable Long id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing event")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public EventResponse update(
            @Parameter(description = "Event id", example = "1") @PathVariable Long id,
            @Valid @RequestBody UpdateEventRequest request) {
        return eventService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Event deleted"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public void delete(
            @Parameter(description = "Event id", example = "1")
            @PathVariable Long id) {
        eventService.delete(id);
    }
}
