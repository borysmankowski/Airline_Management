package org.example.airlinemanagement.application.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateBookingCommand {

    private Integer flightId;
    private List<CreatePassengerCommand> passengers;
    private String email;
}