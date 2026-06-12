package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class CreatePassengerCommand {

    @NotBlank(message = "Name cannot be blank!")
    private String name;
    @NotBlank(message = "Surname cannot be blank!" )
    private String surname;
    @Email
    @NotBlank(message = "Make sure the email address is correct, and it's not empty!")
    private String email;
    @NotBlank(message = "Provide date of birth!")
    private Date birthDate;
}
