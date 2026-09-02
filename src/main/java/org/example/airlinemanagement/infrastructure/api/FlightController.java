package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.airlinemanagement.application.FlightService;
import org.example.airlinemanagement.application.commands.CreateFlightCommand;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FlightDto> createFlight(@RequestBody @Valid CreateFlightCommand createFlightCommand) {
        FlightDto createdFlight = flightService.createFlight(createFlightCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.getAllFlights());


    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<FlightDto>> getFlightsForPerticularAirportAndDates(
            @RequestParam int airportFromId,
            @RequestParam int airportToId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime toDate) {
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(flightService.getFlightsForPerticularAirportAndDates(airportFromId,
                    airportToId,
                    fromDate
            ,toDate));
        }
    }
}

