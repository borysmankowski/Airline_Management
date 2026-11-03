package org.example.airlinemanagement.application.commands.create;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreateFlightCommand {

    @NotBlank(message = "Flight number cannot be blank!")
    @Size(max = 16)
    private String flightNo;
    @NotNull(message = "Airport from cannot be blank!")
    private Integer airportFromId;
    @NotNull(message = "Airport to cannot be blank!")
    private Integer airportToId;
    @NotNull(message = "Departure time cannot be blank!")
    private ZonedDateTime departureTime;
    @NotNull(message = "Arrival time cannot be blank!")
    private ZonedDateTime arrivalTime;
    @NotNull
    @Min(value = 1, message = "Flight needs at least 1 seat")
    private Integer totalSeats;
    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be positive")
    private BigDecimal price;
}
