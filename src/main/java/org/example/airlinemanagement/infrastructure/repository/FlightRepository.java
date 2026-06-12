package org.example.airlinemanagement.infrastructure.repository;

import org.example.airlinemanagement.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
