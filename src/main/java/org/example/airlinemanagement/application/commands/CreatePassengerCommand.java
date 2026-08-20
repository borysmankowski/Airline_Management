package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
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
    @NotNull(message = "Provide date of birth!")
    @Past(message = "Birth date must be in the past!")
    private LocalDate birthDate;
}
