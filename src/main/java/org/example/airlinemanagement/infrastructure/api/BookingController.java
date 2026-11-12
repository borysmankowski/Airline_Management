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
    public ResponseEntity<BookingDto> createBooking(@RequestBody @Valid CreateBookingRequest request) {
        BookingDto created = bookingService.createBooking(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<BookingDto> getMyBookings(Authentication auth) {
        return bookingService.getMyBookings(auth.getName());
    }

    @PostMapping("/{id}/complete")
    public BookingDto complete(@PathVariable int id, Authentication auth) {
        return bookingService.completeBooking(id, auth.getName());
    }

    @PostMapping("/{id}/cancel")
    public BookingDto cancel(@PathVariable int id, Authentication auth) {
        return bookingService.cancelBooking(id, auth.getName());
    }
}
