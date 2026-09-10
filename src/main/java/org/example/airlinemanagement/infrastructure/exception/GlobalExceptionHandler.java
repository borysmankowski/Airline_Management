package org.example.airlinemanagement.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.airlinemanagement.domain.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDto> handleValidationException(ValidationException exception) {
        log.warn("Validation exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(exception.getMessage()));
    }
}