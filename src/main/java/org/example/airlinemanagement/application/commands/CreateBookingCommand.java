package org.example.airlinemanagement.application.commands;

import lombok.Data;

@Data
public class CreateBookingCommand {

    private int flightId;
    private int airportId;
    private int passengerId;
}