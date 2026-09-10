package org.example.airlinemanagement.infrastructure.mapper;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {

    private int id;
    private String flightNo;
    private int airportFromId;
    private int airportToId;
    private ZonedDateTime dateTime;
}
