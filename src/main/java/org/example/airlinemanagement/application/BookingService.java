package org.example.airlinemanagement.application;

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
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new NotFoundException("User " + command.getEmail()));
        Flight flight = flightRepository.findById(command.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight " + command.getFlightId()));

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
                        .orElseGet(() -> passengerRepository.save(Passenger.create(p))))
                .toList();

        Booking booking = Booking.create(flight, user, passengers, now, bookingProperties.timeout());
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Transactional(readOnly = true)
    public List<BookingDto> getMyBookings(String email) {
        return bookingRepository.findByUserEmail(email)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Transactional
    public BookingDto completeBooking(int bookingId, String email) {
        Booking booking = findOwnedBooking(bookingId, email);
        booking.complete(ZonedDateTime.now(clock));
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public BookingDto cancelBooking(int bookingId, String email) {
        Booking booking = findOwnedBooking(bookingId, email);
        booking.cancel();
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public int timeOutExpiredBookings() {
        ZonedDateTime now = ZonedDateTime.now(clock);
        int timedOut = 0;
        for (Booking booking : bookingRepository
                .findByStatusAndExpiresAtLessThanEqual(BookingStatus.IN_PROGRESS, now)) {
            if (booking.timeOut(now)) {
                timedOut++;
            }
        }
        return timedOut;
    }

    private Booking findOwnedBooking(int bookingId, String email) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking " + bookingId));
        if (!booking.getUser().getEmail().equals(email)) {
            throw new NotFoundException("Booking " + bookingId); // 404, so we don't reveal other users' bookings
        }
        return booking;
    }
}

