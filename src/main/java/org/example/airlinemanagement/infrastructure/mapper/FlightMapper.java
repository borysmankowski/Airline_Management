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
                .airportFrom(flight.getAirportFrom())
                .airportTo(flight.getAirportTo())
                .dateTime(flight.getDateTime())
                .build();
    }

    public Flight fromDto (CreateFlightCommand createFlightCommand){
        return Flight.builder()
                .flightNo(createFlightCommand.getFlightNo())
                .airportFrom(createFlightCommand.getAirportFrom())
                .airportTo(createFlightCommand.getAirportTo())
                .dateTime(createFlightCommand.getDateTime())
                .build();
        // TODO: 08/07/2026 powinna byc metoda jak w airport
    }
}
