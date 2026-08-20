package org.example.airlinemanagement.application;

import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateFlightCommand;
import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.example.airlinemanagement.infrastructure.mapper.FlightMapper;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    // TODO: 08/07/2026 zmienic jak w airport service
    @Transactional
    public FlightDto createFlight (CreateFlightCommand createFlightCommand){
        Flight flight = flightMapper.fromDto(createFlightCommand);
        return flightMapper.toDto(flightRepository.save(flight));
    }
}
