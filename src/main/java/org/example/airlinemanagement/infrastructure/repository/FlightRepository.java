package org.example.airlinemanagement.infrastructure.repository;

import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByAirportFromAndAirportToAndDateTimeBetween(
            Airport airportFrom, Airport airportTo, ZonedDateTime from, ZonedDateTime to);
}