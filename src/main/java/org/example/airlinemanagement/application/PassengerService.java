package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreatePassengerCommand;
import org.example.airlinemanagement.domain.Passenger;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.example.airlinemanagement.infrastructure.mapper.PassengerDto;
import org.example.airlinemanagement.infrastructure.mapper.PassengerMapper;
import org.example.airlinemanagement.infrastructure.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Transactional
    public PassengerDto createPassenger(CreatePassengerCommand createPassengerCommand) {
        Passenger passenger = Passenger.create(createPassengerCommand);
        return passengerMapper.toDto(passengerRepository.save(passenger));
    }

    @Transactional(readOnly = true)
    public List<PassengerDto> getAllPassengers(){
        return passengerRepository.findAll()
                .stream()
                .map(passengerMapper::toDto)
                .toList();
    }
}
