package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateAirportCommand;
import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.infrastructure.mapper.AirportDto;
import org.example.airlinemanagement.infrastructure.mapper.AirportMapper;
import org.example.airlinemanagement.infrastructure.repository.AirportRepository;
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
