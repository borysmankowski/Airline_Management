package org.example.airlinemanagement.application.commands;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.airlinemanagement.domain.Role;

@Data
public class ChangeRoleCommand {
    @NotNull
    private Role role;
}
