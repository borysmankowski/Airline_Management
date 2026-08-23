package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateAirportCommand;
import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.infrastructure.mapper.AirportDto;
import org.example.airlinemanagement.infrastructure.mapper.AirportMapper;
import org.example.airlinemanagement.infrastructure.repository.AirportRepository;
import org.example.airlinemanagement.shared.IcaoCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Transactional
    public AirportDto createAirport(CreateAirportCommand createAirportCommand) {
        IcaoCode code = new IcaoCode(createAirportCommand.getIcaoCode());

        if (airportRepository.existsByIcaoCode(code)) {
            throw new EntityNotFoundException(String.valueOf(code));
        }

        Airport airport = Airport.register(code, createAirportCommand.getCountry(), createAirportCommand.getCity());
        return airportMapper.toDto(airportRepository.save(airport));
    }
}

// TODO: 08/07/2026 create / search / delete / security