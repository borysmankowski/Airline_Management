package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDto toDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getFlight().getId(),
                booking.getAirport().getId(),
                booking.getPassenger().getId()
        );
    }
}
