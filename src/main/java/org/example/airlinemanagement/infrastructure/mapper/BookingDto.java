package org.example.airlinemanagement.infrastructure.mapper;


import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public record BookingDto(int id, int flightId, String flightNo, String bookingStatus,
                         ZonedDateTime createdAt, ZonedDateTime expiresAt,
                         BigDecimal totalPrice, List<PassengerDto> passengers) {
}