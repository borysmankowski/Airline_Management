package org.example.airlinemanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.domain.Booking;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final PassengerMapper passengerMapper;

    public BookingDto toDto(Booking b) {
        return new BookingDto(b.getId(), b.getFlight().getId(), b.getFlight().getFlightNo(),
                b.getBookingStatus().name(), b.getCreatedAt(), b.getExpiresAt(),
                b.getTotalPrice().amount(),
                b.getPassengers().stream().map(passengerMapper::toDto).toList());
    }
}
