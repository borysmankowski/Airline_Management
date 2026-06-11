package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.airport.Airport;
import org.example.airlinemanagement.airport.dto.AirportDto;
import org.example.airlinemanagement.airport.dto.AirportMapper;
import org.example.airlinemanagement.airport.dto.CreateAirportCommand;
import org.example.airlinemanagement.infrastructure.AirportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Transactional
    public AirportDto createAirport (CreateAirportCommand createAirportCommand){
        Airport airport = Airport.create(createAirportCommand);
        return airportMapper.toDto(airportRepository.save(airport));
    }
}
