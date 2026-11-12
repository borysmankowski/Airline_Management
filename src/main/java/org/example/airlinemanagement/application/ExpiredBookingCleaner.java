package org.example.airlinemanagement.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpiredBookingCleaner {

    private final BookingService bookingService;

    @Scheduled(fixedDelayString = "${airline.booking.cleanup-interval:PT1M}")
    public void timeOutExpiredBookings() {
        int count = bookingService.timeOutExpiredBookings();
        if (count > 0) {
            log.info("Timed out {} bookings", count);
        }
    }
}