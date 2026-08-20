package org.example.airlinemanagement.infrastructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
}
