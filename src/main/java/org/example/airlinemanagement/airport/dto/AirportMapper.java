package org.example.airlinemanagement.airport.dto;

import lombok.Getter;
import org.example.airlinemanagement.airport.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public AirportDto toDto(Airport airport) {
        return new AirportDto(airport.getIcaoCode(), airport.getCountry(), airport.getCity());
    }
}
