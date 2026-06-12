package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "departure_id")
    private Airport departure;
    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private Airport arrival;
    private ZonedDateTime dateTime;

}
