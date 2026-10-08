package org.example.airlinemanagement.application.commands.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePassengerCommand {

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Surname cannot be blank!")
    private String surname;

    @Email(message = "Make sure the email address is correct!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "Provide date of birth!")
    @Past(message = "Birth date must be in the past!")
    private LocalDate birthDate;
}
