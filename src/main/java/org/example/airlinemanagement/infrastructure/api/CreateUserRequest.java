package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.airlinemanagement.application.commands.create.CreateUserCommand;

public record CreateUserRequest(@NotBlank
                                @Email
                                @Size(max = 255)
                                String email,

                                @NotBlank
                                @Size(min = 8, max = 72, message = "Password must have 8-72 characters")
                                @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                                        message = "Password must contain a lower case letter, an upper case letter and a digit")
                                String password,

                                @NotBlank
                                @Size(max = 100)
                                String name,

                                @NotBlank
                                @Size(max = 100)
                                String surname

) {


    public CreateUserCommand toCommand() {
        return new CreateUserCommand(email, password, name, surname);
    }
}
