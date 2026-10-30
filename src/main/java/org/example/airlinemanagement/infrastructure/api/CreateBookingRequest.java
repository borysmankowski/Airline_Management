package org.example.airlinemanagement.infrastructure.api;

import org.example.airlinemanagement.application.commands.create.CreateBookingCommand;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookingRequest(Integer flightId, List <CreatePassengerRequest> passengerRequests) {

    public CreateBookingCommand toCommand (String email){
        return new CreateBookingCommand(flightId,passengerRequests.stream()
                .map(CreatePassengerRequest::toCommand).collect(Collectors.toList()), email);
    }
}
