package org.example.airlinemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
