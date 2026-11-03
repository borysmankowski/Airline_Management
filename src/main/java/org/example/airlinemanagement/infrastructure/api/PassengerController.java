package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.PassengerService;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;
import org.example.airlinemanagement.infrastructure.mapper.PassengerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody @Valid CreatePassengerCommand createPassengerCommand) {
        PassengerDto createdPassenger = passengerService.createPassenger(createPassengerCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPassenger);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerService.getAllPassengers());
    }
}
