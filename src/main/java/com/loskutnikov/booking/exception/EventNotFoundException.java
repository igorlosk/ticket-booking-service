package com.loskutnikov.booking.exception;

public class EventNotFoundException extends BookingException {

    public EventNotFoundException(Long id) {
        super("Event with id " + id + " not found");
    }

}
