package org.example.airlinemanagement.infrastructure;

import org.example.airlinemanagement.airport.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository <Integer, Airport>{
}
