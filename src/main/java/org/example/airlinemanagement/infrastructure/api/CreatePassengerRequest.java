package org.example.airlinemanagement.infrastructure.api;

import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;

import java.time.LocalDate;

public record CreatePassengerRequest (String name, String surname, String email, LocalDate birthdate) {

    public CreatePassengerCommand toCommand (){
        return new CreatePassengerCommand(name,surname,email,birthdate);
    }

}
