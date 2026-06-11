package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.infrastructure.PassengerRepository;
import org.example.airlinemanagement.passenger.Passenger;
import org.example.airlinemanagement.passenger.dto.CreatePassengerCommand;
import org.example.airlinemanagement.passenger.dto.PassengerDto;
import org.example.airlinemanagement.passenger.dto.PassengerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Transactional
    public PassengerDto createPassenger(CreatePassengerCommand createPassengerCommand) {
        Passenger createdPassenger = passengerMapper.fromDto(createPassengerCommand);
        return passengerMapper.toDto(passengerRepository.save(createdPassenger));
    }
}
