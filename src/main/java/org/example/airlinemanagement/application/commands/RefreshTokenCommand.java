package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenCommand {
    @NotBlank
    private String refreshToken;
}
