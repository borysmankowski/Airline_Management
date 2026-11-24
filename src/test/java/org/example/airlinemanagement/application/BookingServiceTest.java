package org.example.airlinemanagement.application;

import org.example.airlinemanagement.application.commands.create.CreateBookingCommand;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;
import org.example.airlinemanagement.domain.Airport;
import org.example.airlinemanagement.domain.Booking;
import org.example.airlinemanagement.domain.BookingStatus;
import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.domain.Passenger;
import org.example.airlinemanagement.domain.Role;
import org.example.airlinemanagement.domain.User;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.example.airlinemanagement.infrastructure.repository.BookingRepository;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.example.airlinemanagement.infrastructure.repository.PassengerRepository;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.example.airlinemanagement.shared.IcaoCode;
import org.example.airlinemanagement.shared.Money;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookingServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private FlightRepository flightRepository;

    @MockitoBean
    private PassengerRepository passengerRepository;

    @MockitoBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;


    @Test
    void createBooking() {

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(createTestUser()));

        BDDMockito.given(flightRepository.findById(any()))
                .willReturn(Optional.of(createTestFlight()));

        BDDMockito.given(passengerRepository.findByEmail(any()))
                .willReturn(Optional.empty());

        BDDMockito.given(passengerRepository.save(any(Passenger.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        BDDMockito.given(bookingRepository.save(any(Booking.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        BookingDto result = bookingService.createBooking(createBookingCommand());

        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        BDDMockito.then(bookingRepository).should().save(captor.capture());

        BDDMockito.then(passengerRepository).should().save(any(Passenger.class));

        Booking saved = captor.getValue();

        assertThat(result).isNotNull();
        assertThat(saved.getUser()).isSameAs(createTestUser());
        assertThat(saved.getUser().getId()).isSameAs(createTestUser().getId());
        assertThat(saved.getFlight()).isSameAs(createTestFlight());
        assertThat(saved.getStatus()).isEqualTo(BookingStatus.IN_PROGRESS);
    }

    @Test
    void getAllBookings() {
    }

    @Test
    void confirmBooking() {
    }

    @Test
    void cancelBooking() {
    }

    private Flight createTestFlight() {

        Airport from = Airport.register(new IcaoCode("EPPO"), "Poland", "Poznan");
        Airport to = Airport.register(new IcaoCode("EPWA"), "Poland", "Warsaw");

        return Flight.create("LO12", from, to,
                ZonedDateTime.now().plusMinutes(5), ZonedDateTime.now().plusDays(1),
                12, new Money(BigDecimal.valueOf(123)));
    }

    private User createTestUser(){
        return User.create("email@gmail.com", "asd", "Jarek", "Tusk", Role.ADMIN, ZonedDateTime.now());
    }

    private CreatePassengerCommand createPassengerCommand(){
        return new CreatePassengerCommand("Jan", "Kowalski", "jan.kowalski@gmail.com", LocalDate.of(1990, 5, 20));
    }

    private CreateBookingCommand createBookingCommand(){
        return new CreateBookingCommand(1, List.of(createPassengerCommand()), "email@gmail.com");
    }

}