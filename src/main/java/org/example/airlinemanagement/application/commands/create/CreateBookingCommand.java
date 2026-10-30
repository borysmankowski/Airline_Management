package org.example.airlinemanagement.application.commands.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateBookingCommand {

    @NotNull(message = "Flight id is required")
    private Integer flightId;

    @NotEmpty(message = "At least one passenger is required")
    @Size(max = 9, message = "Max 9 passengers per booking")
    private List<@Valid CreatePassengerCommand> passengers;

    private String email;
}