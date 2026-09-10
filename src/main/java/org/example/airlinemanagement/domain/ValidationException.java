package org.example.airlinemanagement.domain;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
