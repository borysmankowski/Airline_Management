package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.create.CreateBookingCommand;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;
import org.example.airlinemanagement.domain.*;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.example.airlinemanagement.infrastructure.mapper.BookingMapper;
import org.example.airlinemanagement.infrastructure.repository.BookingRepository;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.example.airlinemanagement.infrastructure.repository.PassengerRepository;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final BookingProperties properties;
    private final PassengerRepository passengerRepository;
    private final Clock clock;

    @Transactional
    public BookingDto createBooking(CreateBookingCommand createBookingCommand) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email));
        Flight flight = flightRepository.findById(createBookingCommand.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException("Flight " + createBookingCommand.getFlightId()));

        ZonedDateTime now = ZonedDateTime.now(clock);
        if (flight.hasDeparted(now)) {
            throw new ConflictException("Flight has already departed");
        }

        List<CreatePassengerCommand> passengerCommands = createBookingCommand.getPassengers();
        if (passengerCommands.stream().map(CreatePassengerCommand::getEmail).distinct().count() != passengerCommands.size()) {
            throw new ValidationException("The same passenger cannot be added twice");
        }
        List<Passenger> passengers = passengerCommands.stream()
                .map(p -> passengerRepository.findByEmail(p.getEmail())
                        .orElseGet(() -> passengerRepository.save(Passenger.create(p))))
                .toList();

        Booking booking = Booking.create(flight, user, passengers, now, properties.timeout());
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Transactional(readOnly = true)
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
}
