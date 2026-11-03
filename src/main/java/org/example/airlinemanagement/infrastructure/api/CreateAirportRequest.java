package org.example.airlinemanagement.infrastructure.api;

import org.example.airlinemanagement.application.commands.create.CreateAirportCommand;

public record CreateAirportRequest(String icaoCode, String country, String city) {

    public CreateAirportCommand toCommand() {
        return new CreateAirportCommand(icaoCode, country, city);
    }
}
