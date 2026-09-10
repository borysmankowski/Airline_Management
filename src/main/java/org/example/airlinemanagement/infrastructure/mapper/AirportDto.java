package org.example.airlinemanagement.infrastructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record AirportDto(int id, String icaoCode, String country, String city) {
}

