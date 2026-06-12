package org.example.airlinemanagement.application.commands;

import lombok.Data;
import org.example.airlinemanagement.domain.Airport;
import java.time.ZonedDateTime;

@Data
public class CreateFlightCommand {

    private String flightNo;
    private Airport airportFrom;
    private Airport airportTo;
    private ZonedDateTime dateTime;
}
