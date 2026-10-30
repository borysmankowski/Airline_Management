package org.example.airlinemanagement.application;

import org.example.airlinemanagement.domain.Flight;
import org.example.airlinemanagement.domain.Role;
import org.example.airlinemanagement.domain.User;
import org.example.airlinemanagement.infrastructure.repository.FlightRepository;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.example.airlinemanagement.shared.Money;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookingServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private FlightRepository flightRepository;



    @Test
    void createBooking() {

        User user = User.create("email@gmail.com","asd", "Jarek", "Tusk", Role.ADMIN, ZonedDateTime.now());
        Flight flight = Flight.create("LO12", null, null, ZonedDateTime.now().plusMinutes(5), ZonedDateTime.now().plusDays(1), 12, new Money(BigDecimal.valueOf(123)));

        BDDMockito.given(userRepository.findByEmail(any()))
                .willReturn(Optional.of(user));

        BDDMockito.given(flightRepository.findById(any()))
                .willReturn(Optional.of(flight));



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
}