package org.example.airlinemanagement.infrastructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record BookingDto(int id, int flightId, int airportId, int passengerId, String bookingStatus) {
}