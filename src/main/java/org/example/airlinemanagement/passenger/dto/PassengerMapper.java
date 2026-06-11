package org.example.airlinemanagement.passenger.dto;

import org.example.airlinemanagement.passenger.Passenger;
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

    public Passenger fromDto(CreatePassengerCommand createPassengerCommand){
        Passenger passenger = new Passenger();
        passenger.setName(createPassengerCommand.getName());
        passenger.setSurname(createPassengerCommand.getSurname());
        passenger.setEmail(createPassengerCommand.getEmail());
        passenger.setBirthDate(createPassengerCommand.getBirthDate());
        return passenger;
    }


}
