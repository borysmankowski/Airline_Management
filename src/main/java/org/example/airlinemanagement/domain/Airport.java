package org.example.airlinemanagement.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.shared.IcaoCode;

@Entity
@Getter
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "icao_code", unique = true, nullable = false, length = 4))
    private IcaoCode icaoCode;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    private Airport(IcaoCode icaoCode, String country, String city) {
        this.icaoCode = icaoCode;
        this.country = country;
        this.city = city;
    }

    public static Airport register(IcaoCode icaoCode, String country, String city) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country is needed");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is needed");
        }
        return new Airport(icaoCode, country.trim(), city.trim());
    }
}
