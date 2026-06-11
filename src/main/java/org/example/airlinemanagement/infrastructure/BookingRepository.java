package org.example.airlinemanagement.infrastructure;

import org.example.airlinemanagement.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Integer, Booking> {
}
