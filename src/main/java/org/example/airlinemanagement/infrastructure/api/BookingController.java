package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.BookingService;
import org.example.airlinemanagement.application.commands.CreateBookingCommand;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid CreateBookingCommand createBookingCommand) {
        BookingDto createdBooking = bookingService.createBooking(createBookingCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<BookingDto>> getAllAirports() {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getAllBookings());
    }

}
