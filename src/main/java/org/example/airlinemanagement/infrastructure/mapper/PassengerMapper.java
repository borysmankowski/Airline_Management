package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public PassengerDto toDto(Passenger passenger) {

        return new PassengerDto(
                passenger.getId(),
                passenger.getName(),
                passenger.getSurname(),
                passenger.getEmail(),
                passenger.getBirthDate());
    }
}
