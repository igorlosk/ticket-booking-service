package com.loskutnikov.booking.service;

import com.loskutnikov.booking.dto.CreateEventRequest;
import com.loskutnikov.booking.dto.EventResponse;
import com.loskutnikov.booking.dto.UpdateEventRequest;
import com.loskutnikov.booking.exception.EventNotFoundException;
import com.loskutnikov.booking.model.Event;
import com.loskutnikov.booking.model.Status;
import com.loskutnikov.booking.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse create(CreateEventRequest request) {
        Event event = new Event(
                null,
                request.name(),
                request.date(),
                request.venue(),
                Status.AVAILABLE
        );
        Event saved = eventRepository.save(event);
        return EventResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public EventResponse findById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException(id));
        return EventResponse.from(event);
    }

    @Transactional(readOnly = true)
    public Page<EventResponse> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(EventResponse::from);
    }

    public EventResponse update(Long id, UpdateEventRequest request) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException(id));

        event.setName(request.name());
        event.setDate(request.date());
        event.setVenue(request.venue());
        event.setStatus(request.status());

        return EventResponse.from(event);
    }

    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        eventRepository.deleteById(id);
    }
}
