package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateFlightCommand {

    @NotBlank(message = "Flight number cannot be blank!")
    private String flightNo;
    @NotNull(message = "Airport from cannot be blank!")
    private int airportFromId;
    @NotNull(message = "Airport to cannot be blank!")
    private int airportToId;
    @NotNull(message = "Date and time cannot be blank!")
    private ZonedDateTime dateTime;
}
