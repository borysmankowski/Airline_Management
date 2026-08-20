package org.example.airlinemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.example.airlinemanagement.application.commands.CreateAirportCommand;

@Entity
@Getter
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String icaoCode;
    private String country;
    private String city;

    private Airport (String icaoCode, String country, String city){
        this.icaoCode = icaoCode;
        this.country = country;
        this.city = city;
    }

    public static Airport create(CreateAirportCommand createAirportCommand) {
        return new Airport(createAirportCommand.getIcaoCode(), createAirportCommand.getCountry(), createAirportCommand.getCity());
    }
}
