package org.example.airlinemanagement.shared;

import jakarta.persistence.Embeddable;
import org.example.airlinemanagement.domain.ValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record Money(BigDecimal amount) {

    public Money {
        if (amount == null || amount.signum() <= 0) {
            throw new ValidationException("Amount must be positive");
        }
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money multiply(int quantity) {
        return new Money(amount.multiply(BigDecimal.valueOf(quantity)));
    }
}
