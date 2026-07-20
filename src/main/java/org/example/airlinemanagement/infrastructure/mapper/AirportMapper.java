package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public AirportDto toDto(Airport airport) {
        return new AirportDto(airport.getIcaoCode(), airport.getCountry(), airport.getCity());
        // TODO: 08/07/2026 taki mapper tez do flight dto i innych
    }
}
