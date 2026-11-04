package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.constraints.*;
import org.example.airlinemanagement.application.commands.create.CreateFlightCommand;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CreateFlightRequest(
        @NotBlank(message = "Flight number cannot be blank!")
        @Size(max = 16)
        String flightNo,

        @NotNull(message = "Airport from cannot be blank!")
        Integer airportFromId,

        @NotNull(message = "Airport to cannot be blank!")
        Integer airportToId,

        @NotNull(message = "Departure time cannot be blank!")
        ZonedDateTime departureTime,

        @NotNull(message = "Arrival time cannot be blank!")
        ZonedDateTime arrivalTime,

        @NotNull(message = "Total seats cannot be blank!")
        @Min(value = 1, message = "Flight needs at least 1 seat")
        Integer totalSeats,

        @NotNull(message = "Price cannot be blank!")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal price
) {
    public CreateFlightCommand toCommand() {
        return new CreateFlightCommand(flightNo, airportFromId, airportToId,
                departureTime, arrivalTime, totalSeats, price);
    }
}
