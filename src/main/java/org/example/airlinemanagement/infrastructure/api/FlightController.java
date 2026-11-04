package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.FlightService;
import org.example.airlinemanagement.application.commands.update.UpdateFlightCommand;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDto> createFlight(@RequestBody @Valid CreateFlightRequest createFlightCommand) {
        FlightDto createdFlight = flightService.createFlight(createFlightCommand.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.getAllFlights());


    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<FlightDto>> getFlightsForPerticularAirportAndDates(
            @RequestParam int airportFromId,
            @RequestParam int airportToId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime toDate) {
        {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(flightService.getFlightsForPerticularAirportAndDates(airportFromId,
                    airportToId,
                    fromDate
                    , toDate));
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable int id, @RequestBody @Valid UpdateFlightCommand updateFlightCommand) {
        return ResponseEntity.ok(flightService.updateFlight(id, updateFlightCommand));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFlightById(@PathVariable int id) {
        flightService.deleteFlightById(id);
    }
}

