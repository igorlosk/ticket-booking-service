package com.loskutnikov.booking.exception;

public class UsernameAlreadyExistsException extends BookingException {

    public UsernameAlreadyExistsException(String username) {
        super("User with username '" + username + "' already exists");
    }
}
