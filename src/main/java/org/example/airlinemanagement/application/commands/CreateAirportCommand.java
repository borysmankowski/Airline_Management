package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.example.airlinemanagement.shared.IcaoCode;

@Data
public class CreateAirportCommand {

    @Pattern(regexp = "^[A-Za-z]{4}$", message = "ICAO code must be 4 letters")
    @NotBlank(message = "ICAO code cannot be blank")
    @NotNull
    private String icaoCode;
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @NotBlank(message = "City cannot be blank")
    private String city;
}
