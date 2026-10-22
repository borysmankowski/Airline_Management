package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.AirportService;
import org.example.airlinemanagement.application.commands.create.CreateAirportCommand;
import org.example.airlinemanagement.infrastructure.mapper.AirportDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AirportDto> createAirport(@RequestBody @Valid CreateAirportCommand createAirportCommand) {
        AirportDto createdAirport = airportService.createAirport(createAirportCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(airportService.getAllAirports());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirportById(@PathVariable("id") int idToDelete) {
        airportService.deleteAirportById(idToDelete);
    }
}
