package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.BookingService;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid CreateBookingRequest request, Authentication auth) {
        String email = auth.getName();
        BookingDto createdBooking = bookingService.createBooking(request.toCommand(email));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<BookingDto>> getAllAirports() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingService.getAllBookings());
    }

    @PostMapping("/{id}/confirm")
    public BookingDto confirm(@PathVariable int id) {
        return bookingService.confirmBooking(id);
    }

    @PostMapping("/{id}/cancel")
    public BookingDto cancel(@PathVariable int id) {
        return bookingService.cancelBooking(id);
    }

}
