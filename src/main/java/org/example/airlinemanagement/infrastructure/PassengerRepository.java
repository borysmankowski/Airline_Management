package org.example.airlinemanagement.infrastructure;

import org.example.airlinemanagement.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository <Passenger, Integer>{
}
