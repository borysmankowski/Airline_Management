package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String flightNo;
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Airport airportFrom;
    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private Airport airportTo;
    private ZonedDateTime dateTime;

    private Flight(String flightNo, Airport airportFrom, Airport airportTo, ZonedDateTime dateTime) {
        this.flightNo = flightNo;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.dateTime = dateTime;
    }


    public static Flight create(String flightNo, Airport airportFrom, Airport airportTo, ZonedDateTime zonedDateTime) {
        return new Flight(flightNo, airportFrom, airportTo, zonedDateTime);
    }
}
