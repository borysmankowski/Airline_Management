package org.example.airlinemanagement.infrastructure.mapper;

import org.example.airlinemanagement.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getRole());
    }
}