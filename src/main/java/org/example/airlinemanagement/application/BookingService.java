package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.create.CreateBookingCommand;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;
import org.example.airlinemanagement.domain.Booking;
import org.example.airlinemanagement.domain.ConflictException;
import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.domain.Passenger;
import org.example.airlinemanagement.domain.User;
import org.example.airlinemanagement.domain.ValidationException;
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
    private final BookingProperties bookingProperties;
    private final PassengerRepository passengerRepository;
    private final Clock clock;

    @Transactional
    public BookingDto createBooking(CreateBookingCommand command) {
        // TODO: 22/10/2026 przekazac do booking - ma powstac booking np na 15 min a pozniej jak potwierdzone to spoko a jak nie to cancelled
        // ustawic cron na bookingi ktore sa juz expired i usunac - DONE
        // sprawdzac w db ktore booking maja created time wiecej niz =>15 min i wtedy usuwac -DONE
        // TODO: 29/10/2026 Przemapowac wszystkie commandy na requesty aby oddzielic warstwy
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User " + command));
        Flight flight = flightRepository.findById(command.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException("Flight " + command.getFlightId()));

        ZonedDateTime now = ZonedDateTime.now(clock);
        if (flight.hasDeparted(now)) {
            throw new ConflictException("Flight has already departed");
        }

        List<CreatePassengerCommand> passengerCommands = command.getPassengers();
        if (passengerCommands.stream().map(CreatePassengerCommand::getEmail).distinct().count() != passengerCommands.size()) {
            throw new ValidationException("The same passenger cannot be added twice");
        }
        List<Passenger> passengers = passengerCommands.stream()
                .map(p -> passengerRepository.findByEmail(p.getEmail())
                        // TODO: 29/10/2026 zamokowac find by email do testu i dodac
                        .orElseGet(() -> passengerRepository.save(Passenger.create(p))))
                .toList();

        Booking booking = Booking.create(flight, user, passengers, now, bookingProperties.timeout());
        return bookingMapper.toDto(bookingRepository.save(booking)); // znalezc jakos booking czy zostal dodany do bazy danych
        // asertThat
    }

    @Transactional(readOnly = true)
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Transactional
    public BookingDto confirmBooking(int bookingId) {
        Booking booking = findOwnedBooking(bookingId);
        booking.confirm(ZonedDateTime.now(clock));
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public BookingDto cancelBooking(int bookingId) {
        Booking booking = findOwnedBooking(bookingId);
        booking.cancel();
        return bookingMapper.toDto(booking);
    }

    private Booking findOwnedBooking(int bookingId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking " + bookingId));
        if (!booking.getUser().getEmail().equals(email)) {
            throw new EntityNotFoundException("Booking " + bookingId);
        }
        return booking;
    }
}

