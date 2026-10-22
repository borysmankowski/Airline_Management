package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.commands.create.CreateFlightCommand;
import org.example.airlinemanagement.application.commands.update.UpdateFlightCommand;
import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.example.airlinemanagement.infrastructure.mapper.FlightMapper;
import org.example.airlinemanagement.infrastructure.repository.AirportRepository;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.example.airlinemanagement.shared.Money;
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
        Airport airportFrom = airportRepository.findById(createFlightCommand.getAirportFromId())
                .orElseThrow(() -> new EntityNotFoundException("Airport " + createFlightCommand.getAirportFromId()));
        Airport airportTo = airportRepository.findById(createFlightCommand.getAirportToId())
                .orElseThrow(() -> new EntityNotFoundException("Airport " + createFlightCommand.getAirportToId()));
        Flight flight = Flight.create(
                createFlightCommand.getFlightNo(),
                airportFrom,
                airportTo,
                createFlightCommand.getDepartureTime(),
                createFlightCommand.getArrivalTime(),
                createFlightCommand.getTotalSeats(),
                new Money(createFlightCommand.getPrice()));
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
            return flightRepository.findByAirportFromAndAirportToAndDepartureTimeBetween(airportFrom, airportTo, fromDate, toDate)
                    .stream()
                    .map(flightMapper::toDto)
                    .toList();
        }
    }

    @Transactional
    public FlightDto updateFlight (int id, UpdateFlightCommand updateFlightCommand){
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight of id: " + id + "was not found"));

        Airport airportFrom = airportRepository.findById(updateFlightCommand.getAirportFromId())
                .orElseThrow(() -> new EntityNotFoundException("Airport from of id: " + updateFlightCommand.getAirportFromId() + "was not found"));

        Airport airportTo = airportRepository.findById(updateFlightCommand.getAirportToId())
                .orElseThrow(() -> new EntityNotFoundException("Airport from of id: " + updateFlightCommand.getAirportToId() + "was not found"));

        flight.update(
                updateFlightCommand.getFlightNo(),
                airportFrom,
                airportTo,
                updateFlightCommand.getDepartureTime(),
                updateFlightCommand.getArrivalTime(),
                updateFlightCommand.getTotalSeats(),
                new Money(updateFlightCommand.getPrice()));

        return flightMapper.toDto(flight);
    }

    @Transactional
    public void deleteFlightById(int id) {
        if (!flightRepository.existsById(id)) {
            throw new EntityNotFoundException("Flight with id: " + id + " was not found!");
        }
        flightRepository.deleteById(id);
    }
}
