package org.example.airlinemanagement.infrastructure.repository;

import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.shared.IcaoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    boolean existsByIcaoCode(IcaoCode code);
}
