package org.example.airlinemanagement.flight.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.airlinemanagement.airport.Airport;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private String flightNo;
    private Airport airportFrom;
    private Airport airportTo;
    private ZonedDateTime dateTime;
}
