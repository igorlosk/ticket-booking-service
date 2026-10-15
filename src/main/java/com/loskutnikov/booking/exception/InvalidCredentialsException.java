package com.loskutnikov.booking.exception;

public class InvalidCredentialsException extends BookingException {

    public InvalidCredentialsException() {
        super("Invalid username or password");
    }
}
