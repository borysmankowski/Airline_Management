package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.airlinemanagement.application.commands.create.CreateBookingCommand;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookingRequest(
        @NotNull(message = "Flight id is required")
        Integer flightId,

        @NotEmpty(message = "At least one passenger is required")
        @Size(max = 9, message = "Max 9 passengers per booking")
        List<@Valid CreatePassengerRequest> passengers,

        @Email(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email must be valid")
        String email
) {

    public CreateBookingCommand toCommand() {
        return new CreateBookingCommand(flightId, passengers.stream()
                .map(CreatePassengerRequest::toCommand).collect(Collectors.toList()), email);
    }
}
