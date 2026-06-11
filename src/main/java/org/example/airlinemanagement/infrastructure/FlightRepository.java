package org.example.airlinemanagement.infrastructure;

import org.example.airlinemanagement.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Integer, Flight> {
}
