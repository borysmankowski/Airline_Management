package org.example.airlinemanagement.infrastructure.repository;

import org.example.airlinemanagement.domain.Booking;
import org.example.airlinemanagement.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Modifying
    @Query("""
            update Booking b
            set b.status = :cancelled
            where b.status = :pending and b.expiresAt <= :now
            """)
    int cancelExpired(@Param("now") ZonedDateTime now,
                      @Param("pending") BookingStatus pending,
                      @Param("cancelled") BookingStatus cancelled);
}
