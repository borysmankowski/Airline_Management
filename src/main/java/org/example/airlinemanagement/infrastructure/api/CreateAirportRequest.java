package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.airlinemanagement.application.commands.create.CreateAirportCommand;

public record CreateAirportRequest(
        @Pattern(regexp = "^[A-Za-z]{4}$", message = "ICAO code must be 4 letters")
        @NotBlank(message = "ICAO code cannot be blank")
        @NotNull
        String icaoCode,

        @NotBlank(message = "Country cannot be blank")
        String country,

        @NotBlank(message = "City cannot be blank")
        String city
) {

    public CreateAirportCommand toCommand() {
        return new CreateAirportCommand(icaoCode, country, city);
    }
}
