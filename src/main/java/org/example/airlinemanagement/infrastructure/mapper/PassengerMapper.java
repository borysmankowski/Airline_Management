package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.application.commands.CreatePassengerCommand;
import org.example.airlinemanagement.domain.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public PassengerDto toDto(Passenger passenger){
        PassengerDto dto = new PassengerDto();
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setSurname(passenger.getSurname());
        dto.setEmail(passenger.getEmail());
        dto.setBirthDate(passenger.getBirthDate());
        return dto;
    }

    public Passenger fromCommand(CreatePassengerCommand createPassengerCommand){
        Passenger passenger = new Passenger();
        passenger.setName(createPassengerCommand.getName());
        passenger.setSurname(createPassengerCommand.getSurname());
        passenger.setEmail(createPassengerCommand.getEmail());
        passenger.setBirthDate(createPassengerCommand.getBirthDate());
        return passenger;
    }


}
