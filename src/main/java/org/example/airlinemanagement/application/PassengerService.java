package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreatePassengerCommand;
import org.example.airlinemanagement.infrastructure.repository.PassengerRepository;
import org.example.airlinemanagement.domain.Passenger;
import org.example.airlinemanagement.infrastructure.mapper.PassengerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Transactional
    public Passenger createPassenger(CreatePassengerCommand createPassengerCommand) {
        Passenger createdPassenger = passengerMapper.fromCommand(createPassengerCommand);
        return passengerRepository.save(createdPassenger);
    }
}
