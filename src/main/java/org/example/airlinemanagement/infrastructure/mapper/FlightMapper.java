package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toDto(Flight flight) {
        return new FlightDto(
                flight.getId(),
                flight.getFlightNo(),
                flight.getAirportFrom().getId(),
                flight.getAirportTo().getId(),
                flight.getDepartureTime(),
                flight.getArrivalTime()
        );
    }
}
