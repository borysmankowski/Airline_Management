package org.example.airlinemanagement.domain;

public class BookingExpiredException extends ConflictException {
    public BookingExpiredException() { super("Booking has expired"); }
}