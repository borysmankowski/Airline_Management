package org.example.airlinemanagement.application.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightCommand {

    private String flightNo;
    private Integer airportFromId;
    private Integer airportToId;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;
    private Integer totalSeats;
    private BigDecimal price;
}
