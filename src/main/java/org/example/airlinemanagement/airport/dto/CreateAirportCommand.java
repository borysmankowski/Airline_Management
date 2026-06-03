package org.example.airlinemanagement.airport.dto;

import lombok.Data;

@Data
public class CreateAirportCommand {

    private String icaoCode;
    private String country;
    private String city;
}
