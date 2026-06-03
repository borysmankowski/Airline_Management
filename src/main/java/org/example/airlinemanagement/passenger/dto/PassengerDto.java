package org.example.airlinemanagement.passenger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PassengerDto {

    private String name;
    private String surname;
    private String email;
    private Date birthDate;
}
