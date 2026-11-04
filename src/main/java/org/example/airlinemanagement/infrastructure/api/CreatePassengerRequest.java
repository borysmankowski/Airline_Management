package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;

import java.time.LocalDate;

public record CreatePassengerRequest(
        @NotBlank(message = "Name cannot be blank!")
        String name,

        @NotBlank(message = "Surname cannot be blank!")
        String surname,

        @Email(message = "Make sure the email address is correct!")
        @NotBlank(message = "Email cannot be empty!")
        String email,

        @NotNull(message = "Provide date of birth!")
        @Past(message = "Birth date must be in the past!")
        LocalDate birthDate
) {


    public CreatePassengerCommand toCommand() {
        return new CreatePassengerCommand(name, surname, email, birthDate);
    }

}
