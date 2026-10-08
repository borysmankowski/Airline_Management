package org.example.airlinemanagement.security;

import org.springframework.security.core.Authentication;

import java.util.Objects;

public record Caller(String email, boolean admin) {

    public static Caller from(Authentication auth) {
        boolean admin = auth.getAuthorities().stream()
                .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_ADMIN"));
        return new Caller(auth.getName(), admin);
    }
}