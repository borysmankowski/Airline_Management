package org.example.airlinemanagement.application.commands.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAirportCommand {
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @NotBlank(message = "City cannot be blank")
    private String city;
}