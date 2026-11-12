package org.example.airlinemanagement.infrastructure.repository;

import org.example.airlinemanagement.domain.Booking;
import org.example.airlinemanagement.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserEmail(String email);

    List<Booking> findByStatusAndExpiresAtLessThanEqual(BookingStatus status, ZonedDateTime now);
}
