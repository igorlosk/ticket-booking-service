package com.loskutnikov.booking.controller;

import com.loskutnikov.booking.dto.CreateEventRequest;
import com.loskutnikov.booking.dto.EventResponse;
import com.loskutnikov.booking.dto.UpdateEventRequest;
import com.loskutnikov.booking.service.EventService;
import com.sun.jdi.request.EventRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> create (@Valid @RequestBody CreateEventRequest request) {
        EventResponse created = eventService.create(request);
        URI location = URI.create("/api/v1/events/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<EventResponse> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventResponse findById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    public EventResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEventRequest request) {
        return eventService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

}
