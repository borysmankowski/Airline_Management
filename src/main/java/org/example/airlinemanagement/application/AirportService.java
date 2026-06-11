package org.example.airlinemanagement.application;

import lombok.AllArgsConstructor;
import org.example.airlinemanagement.infrastructure.AirportRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
}
