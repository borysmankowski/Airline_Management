package org.example.airlinemanagement.application.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreatePassengerCommand {

    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
}
