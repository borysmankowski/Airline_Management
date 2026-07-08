package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.airlinemanagement.domain.Airport;
import java.time.ZonedDateTime;

@Data
public class CreateFlightCommand {

    @NotBlank(message = "Flight number cannot be blank!")
    private String flightNo;
    @NotBlank(message = "Airport from cannot be blank!")
    private Airport airportFrom;
    @NotBlank(message = "Airport to cannot be blank!")
    private Airport airportTo;
    @NotBlank(message = "Date and time cannot be blank!")
    private ZonedDateTime dateTime;
}
