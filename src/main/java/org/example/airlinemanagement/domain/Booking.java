package org.example.airlinemanagement.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.shared.Money;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Passenger> passengers = new HashSet<>(); // nie uzwac list w relacji zmien na set jak nie potrzeba kolejnosci to set

    // a jak ma byc koljenosc to ma byc adnotacja OrderColumn i podac id aby zachowac kolejnosc
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