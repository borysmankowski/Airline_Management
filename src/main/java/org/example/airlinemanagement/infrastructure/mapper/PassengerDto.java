package org.example.airlinemanagement.infrastructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public record PassengerDto(int id, String name, String surname, String email, LocalDate birthDate) {
}
