package org.example.airlinemanagement.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.airlinemanagement.domain.BookingStatus;
import org.example.airlinemanagement.infrastructure.repository.BookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpiredBookingCleaner {

    private final BookingRepository bookingRepository;
    private final Clock clock;

    @Scheduled(fixedDelayString = "${airline.booking.cleanup-interval:PT1M}")
    @Transactional
    public void cancelExpiredBookings() {
        int count = bookingRepository.cancelExpired(
                ZonedDateTime.now(clock),
                BookingStatus.PENDING,
                BookingStatus.CANCELLED);
        if (count > 0) {
            log.info("Cancelled {} expired bookings", count);
        }
        // TODO: 29/10/2026 awaitAbility
    }
}