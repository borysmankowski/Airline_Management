package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateBookingCommand;
import org.example.airlinemanagement.domain.*;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.example.airlinemanagement.infrastructure.mapper.BookingMapper;
import org.example.airlinemanagement.infrastructure.repository.AirportRepository;
import org.example.airlinemanagement.infrastructure.repository.BookingRepository;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.example.airlinemanagement.infrastructure.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto createBooking(CreateBookingCommand createBookingCommand) {
        Flight flight = flightRepository.findById(createBookingCommand.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException("Flight " + createBookingCommand.getFlightId()));
        Airport airport = airportRepository.findById(createBookingCommand.getAirportId())
                .orElseThrow(() -> new EntityNotFoundException("Airport " + createBookingCommand.getAirportId()));
        Passenger passenger = passengerRepository.findById(createBookingCommand.getPassengerId())
                .orElseThrow(() -> new EntityNotFoundException("Passenger " + createBookingCommand.getPassengerId()));

        return bookingMapper.toDto(bookingRepository.save(Booking.create(flight, airport, passenger, BookingStatus.IN_PROGRESS)));
    }

    @Transactional(readOnly = true)
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
}
