package org.example.airlinemanagement.airport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAirportCommand {

    @Pattern(regexp = "^[A-Za-z]{4}$", message = "ICAO code must be 4 letters")
    @NotBlank(message = "ICAO code cannot be blank")
    private String icaoCode;
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @NotBlank(message = "City cannot be blank")
    private String city;
}
