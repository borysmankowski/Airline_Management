package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.application.commands.CreateBookingCommand;
import org.example.airlinemanagement.domain.Booking;
import org.example.airlinemanagement.infrastructure.api.BookingController;
import org.example.airlinemanagement.infrastructure.mapper.BookingDto;
import org.example.airlinemanagement.infrastructure.mapper.BookingMapper;
import org.example.airlinemanagement.infrastructure.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto createBooking (CreateBookingCommand createBookingCommand){
        Booking booking = Booking.create(createBookingCommand);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }
}
