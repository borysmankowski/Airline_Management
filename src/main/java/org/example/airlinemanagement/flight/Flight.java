package org.example.airlinemanagement.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.airlinemanagement.airport.Airport;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String flightNo;
    @ManyToOne
    private Airport airportFrom;
    @ManyToOne
    private Airport airportTo;
    private ZonedDateTime dateTime;
}
