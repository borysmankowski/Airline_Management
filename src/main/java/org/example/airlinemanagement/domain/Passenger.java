package org.example.airlinemanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.airlinemanagement.application.commands.CreatePassengerCommand;
import org.example.airlinemanagement.domain.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings;

    public Passenger(String name, String surname, String email, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.bookings = new ArrayList<>();
    }

    public static Passenger create(CreatePassengerCommand createPassengerCommand){
        if (createPassengerCommand.getName() == null || createPassengerCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be blank!");
        }
        if (createPassengerCommand.getSurname() == null || createPassengerCommand.getSurname().isBlank()){
            throw new IllegalArgumentException("Surname cannot be blank!");
        }
        if (createPassengerCommand.getEmail() == null || createPassengerCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be blank!");
        }
        if (createPassengerCommand.getBirthDate() == null || createPassengerCommand.getBirthDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date of birth cannot be blank!");
        }

        return new Passenger(createPassengerCommand.getName(),
                createPassengerCommand.getSurname(),
                createPassengerCommand.getEmail(),
                createPassengerCommand.getBirthDate());
    }
}
