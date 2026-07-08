package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.FlightService;
import org.example.airlinemanagement.application.commands.CreateFlightCommand;
import org.example.airlinemanagement.infrastructure.mapper.FlightDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
