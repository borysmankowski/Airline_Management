package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.AirportService;
import org.example.airlinemanagement.application.commands.CreateAirportCommand;
import org.example.airlinemanagement.infrastructure.mapper.AirportDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AirportDto> createAirport(@RequestBody @Valid CreateAirportCommand createAirportCommand) {
        AirportDto createdAirport = airportService.createAirport(createAirportCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.getAllAirports());
    }
}
