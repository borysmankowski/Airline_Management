package org.example.airlinemanagement.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPassengerException extends RuntimeException {

    public InvalidPassengerException(String message) {
        super(message);
    }
}
