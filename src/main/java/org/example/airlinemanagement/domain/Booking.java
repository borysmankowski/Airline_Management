package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price", nullable = false, precision = 10, scale = 2))
    private Money totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @ManyToMany
    @JoinTable(name = "booking_passengers",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private Set<Passenger> passengers = new HashSet<>();

    public static Booking create(Flight flight, User user, List<Passenger> passengers,
                                 ZonedDateTime now, Duration timeout) {
        if (passengers == null || passengers.isEmpty()) {
            throw new ValidationException("Booking needs at least one passenger");
        }
        flight.reserveSeats(passengers.size());

        Booking booking = new Booking();
        booking.flight = flight;
        booking.user = user;
        booking.status = BookingStatus.IN_PROGRESS;
        booking.createdAt = now;
        booking.expiresAt = now.plus(timeout);
        booking.totalPrice = flight.getPrice().multiply(passengers.size());
        booking.passengers.addAll(passengers);
        return booking;
    }

    public boolean isExpired(ZonedDateTime now) {
        return !now.isBefore(expiresAt);
    }

    public void complete(ZonedDateTime now) {
        if (status == BookingStatus.COMPLETED) return;
        if (status != BookingStatus.IN_PROGRESS)
            throw new ConflictException("Booking cannot be completed from status " + status);
        if (isExpired(now)) throw new BookingExpiredException();
        status = BookingStatus.COMPLETED;
    }

    public void cancel() {
        if (status == BookingStatus.CANCELLED) return;
        if (status == BookingStatus.TIMED_OUT)
            throw new ConflictException("Timed out booking cannot be cancelled");
        status = BookingStatus.CANCELLED;
        flight.releaseSeats(passengers.size());
    }

    public boolean timeOut(ZonedDateTime now) {
        if (status != BookingStatus.IN_PROGRESS || !isExpired(now))
            return false;

        status = BookingStatus.TIMED_OUT;
        flight.releaseSeats(passengers.size());
        return true;
    }


}