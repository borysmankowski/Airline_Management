package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.airlinemanagement.application.commands.CreateFlightCommand;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
