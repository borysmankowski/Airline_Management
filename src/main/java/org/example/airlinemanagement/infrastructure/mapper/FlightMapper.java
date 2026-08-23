package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.application.commands.CreateFlightCommand;
import org.example.airlinemanagement.domain.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toDto (Flight flight){
        return FlightDto.builder()
                .id(flight.getId())
                .flightNo(flight.getFlightNo())
                .airportFromId(flight.getAirportFrom().getId())
                .airportToId(flight.getAirportTo().getId())
                .dateTime(flight.getDateTime())
                .build();
    }
}
