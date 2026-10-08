package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.shared.Money;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price", nullable = false, precision = 10, scale = 2))
    private Money totalPrice;

    @ManyToMany
    @JoinTable(name = "booking_passengers",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengers = new ArrayList<>();

    public static Booking create(Flight flight, User user, List<Passenger> passengers,
                                 ZonedDateTime now, Duration timeout) {
        if (passengers == null || passengers.isEmpty()) {
            throw new ValidationException("Booking needs at least one passenger");
        }
        flight.reserveSeats(passengers.size());

        Booking booking = new Booking();
        booking.flight = flight;
        booking.user = user;
        booking.bookingStatus = BookingStatus.IN_PROGRESS;
        booking.createdAt = now;
        booking.expiresAt = now.plus(timeout);
        booking.totalPrice = flight.getPrice().multiply(passengers.size());
        booking.passengers.addAll(passengers);
        return booking;
    }
}