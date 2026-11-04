package org.example.airlinemanagement.application.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAirportCommand {

    private String icaoCode;
    private String country;
    private String city;
}
