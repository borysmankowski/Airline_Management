package org.example.airlinemanagement.infrastructure.mapper;

import lombok.*;

import java.time.ZonedDateTime;

public record FlightDto(int id, String flightNo, int airportFromId, int airportToId, ZonedDateTime dateTime) {
}
