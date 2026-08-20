package org.example.airlinemanagement.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPassengerException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(InvalidPassengerException exception) {
        log.error("Invalid passenger exception", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto("Bad Request!"));
    }

}