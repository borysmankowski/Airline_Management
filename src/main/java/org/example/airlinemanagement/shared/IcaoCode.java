package org.example.airlinemanagement.shared;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityNotFoundException;

import java.util.regex.Pattern;

@Embeddable
public record IcaoCode(String value) {

    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{4}$");

    public IcaoCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Kod ICAO jest wymagany");
        }
        value = value.trim().toUpperCase();
        if (!PATTERN.matcher(value).matches()) {
            throw new EntityNotFoundException(value);
        }
    }
}