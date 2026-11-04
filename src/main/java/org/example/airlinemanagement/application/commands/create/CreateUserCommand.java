package org.example.airlinemanagement.application.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand {

    private String email;
    private String password;
    private String name;
    private String surname;
}