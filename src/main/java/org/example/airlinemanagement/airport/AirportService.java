package org.example.airlinemanagement.airport;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
}
