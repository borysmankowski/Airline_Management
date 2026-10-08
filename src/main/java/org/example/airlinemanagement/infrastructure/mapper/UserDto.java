package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.Role;

public record UserDto(int id, String email, String name, String surname, Role role) {
}
