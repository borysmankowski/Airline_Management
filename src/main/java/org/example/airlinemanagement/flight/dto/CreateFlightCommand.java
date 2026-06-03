package org.example.airlinemanagement.flight.dto;

import lombok.Data;
import org.example.airlinemanagement.airport.Airport;
import java.time.ZonedDateTime;

@Data
public class CreateFlightCommand {

    private String flightNo;
    private Airport airportFrom;
    private Airport airportTo;
    private ZonedDateTime dateTime;
}
