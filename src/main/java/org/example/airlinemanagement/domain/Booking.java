package org.example.airlinemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private Booking(Flight flight, Airport airport, Passenger passenger, BookingStatus bookingStatus) {
        this.flight = flight;
        this.airport = airport;
        this.passenger = passenger;
        this.bookingStatus = bookingStatus;
    }

    public static Booking create(Flight flight, Airport airport, Passenger passenger, BookingStatus bookingStatus) {
        return new Booking(flight, airport, passenger, bookingStatus);
    }
}