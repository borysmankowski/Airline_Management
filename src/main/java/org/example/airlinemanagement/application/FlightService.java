package org.example.airlinemanagement.application;

import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateFlightCommand;
import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.example.airlinemanagement.infrastructure.mapper.FlightMapper;
import org.example.airlinemanagement.infrastructure.repository.AirportRepository;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportRepository airportRepository;

    @Transactional
    public FlightDto createFlight(CreateFlightCommand createFlightCommand) {
        Airport airportFrom = airportRepository.findById(createFlightCommand.getAirportFromId()).orElseThrow();
        Airport airportTo = airportRepository.findById(createFlightCommand.getAirportToId()).orElseThrow();
        Flight flight = Flight.create(createFlightCommand.getFlightNo(), airportFrom, airportTo, createFlightCommand.getDateTime());
        return flightMapper.toDto(flightRepository.save(flight));
    }

    @Transactional(readOnly = true)
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FlightDto> getFlightsForPerticularAirportAndDates(
            int airportFromId, int airportToId, ZonedDateTime fromDate, ZonedDateTime toDate) {

        Airport airportFrom = airportRepository.findById(airportFromId).orElseThrow();
        Airport airportTo = airportRepository.findById(airportToId).orElseThrow();

        {
            return flightRepository.findByAirportFromAndAirportToAndDateTimeBetween(airportFrom, airportTo, fromDate, toDate)
                    .stream()
                    .map(flightMapper::toDto)
                    .toList();
        }
    }
}
