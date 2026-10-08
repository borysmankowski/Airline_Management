package org.example.airlinemanagement.infrastructure.mapper;

import java.time.LocalDate;

public record PassengerDto(int id, String name, String surname, String email, LocalDate birthDate) {
}
