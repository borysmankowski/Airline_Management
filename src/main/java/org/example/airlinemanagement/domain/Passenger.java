package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.application.commands.create.CreatePassengerCommand;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    @ManyToMany(mappedBy = "passengers")
    private List<Booking> bookings;

    public Passenger(String name, String surname, String email, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.bookings = new ArrayList<>();
    }

    public static Passenger create(CreatePassengerCommand createPassengerCommand) {
        if (createPassengerCommand.getName() == null || createPassengerCommand.getName().isBlank()) {
            throw new ValidationException("Name cannot be blank!");
        }
        if (createPassengerCommand.getSurname() == null || createPassengerCommand.getSurname().isBlank()) {
            throw new ValidationException("Surname cannot be blank!");
        }
        if (createPassengerCommand.getEmail() == null || createPassengerCommand.getEmail().isBlank()) {
            throw new ValidationException("Email cannot be blank!");
        }
        if (createPassengerCommand.getBirthDate() == null || createPassengerCommand.getBirthDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Date of birth cannot be blank!");
        }

        return new Passenger(createPassengerCommand.getName(),
                createPassengerCommand.getSurname(),
                createPassengerCommand.getEmail(),
                createPassengerCommand.getBirthDate());
    }
}
