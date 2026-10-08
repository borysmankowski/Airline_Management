package org.example.airlinemanagement.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("airline.booking")
public record BookingProperties(Duration timeout) {
    public BookingProperties {
        if (timeout == null) timeout = Duration.ofMinutes(15);
    }
}