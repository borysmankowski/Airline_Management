package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.shared.Money;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 16)
    private String flightNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_id")
    private Airport airportFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrival_id")
    private Airport airportTo;

    @Column(nullable = false)
    private ZonedDateTime departureTime;

    @Column(nullable = false)
    private ZonedDateTime arrivalTime;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private int availableSeats;

    @Getter
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price", nullable = false, precision = 10, scale = 2))
    private Money price;

    @Version
    private Long version;

    private Flight(String flightNo, Airport from, Airport to, ZonedDateTime departure,
                   ZonedDateTime arrival, int totalSeats, Money price) {
        this.flightNo = flightNo;
        this.airportFrom = from;
        this.airportTo = to;
        this.departureTime = departure;
        this.arrivalTime = arrival;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.price = price;
    }

    public static Flight create(String flightNo, Airport from, Airport to, ZonedDateTime departure,
                                ZonedDateTime arrival, int totalSeats, Money price) {
        validate(flightNo, from, to, departure, arrival, totalSeats);
        return new Flight(flightNo.trim(), from, to, departure, arrival, totalSeats, price);
    }

    private static void validate(String flightNo, Airport from, Airport to, ZonedDateTime departure,
                                 ZonedDateTime arrival, int totalSeats) {
        if (flightNo == null || flightNo.isBlank()) throw new ValidationException("Flight number is required");
        if (from.getIcaoCode().equals(to.getIcaoCode()))
            throw new ValidationException("Departure and arrival airports must differ");
        if (!arrival.isAfter(departure)) throw new ValidationException("Arrival must be after departure");
        if (totalSeats < 1) throw new ValidationException("Flight must have at least 1 seat");
    }

    public void update(String flightNo, Airport from, Airport to, ZonedDateTime departure,
                       ZonedDateTime arrival, int totalSeats, Money price) {
        validate(flightNo, from, to, departure, arrival, totalSeats);
        int booked = bookedSeats();
        if (totalSeats < booked) {
            throw new ConflictException("Cannot reduce seats below already booked: " + booked);
        }
        this.flightNo = flightNo.trim();
        this.airportFrom = from;
        this.airportTo = to;
        this.departureTime = departure;
        this.arrivalTime = arrival;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats - booked;
        this.price = price;
    }

    public void reserveSeats(int count) {
        if (count <= 0) {
            throw new ValidationException("Seat count must be positive");
        }
        if (count > availableSeats) {
            throw new ConflictException("Not enough free seats: requested %d, available %d"
                    .formatted(count, availableSeats));
        }
        availableSeats -= count;
    }

    public int bookedSeats() {
        return totalSeats - availableSeats;
    }

    public boolean hasDeparted(ZonedDateTime now) {
        return !departureTime.isAfter(now);
    }
}
