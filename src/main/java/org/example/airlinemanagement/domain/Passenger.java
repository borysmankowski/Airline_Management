package org.example.airlinemanagement.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.example.airlinemanagement.application.commands.CreatePassengerCommand;
import org.example.airlinemanagement.infrastructure.exception.InvalidPassengerException;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    @Getter
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
            throw new InvalidPassengerException("Name cannot be blank!");
        }
        if (createPassengerCommand.getSurname() == null || createPassengerCommand.getSurname().isBlank()){
            throw new InvalidPassengerException("Surname cannot be blank!");
        }
        if (createPassengerCommand.getEmail() == null || createPassengerCommand.getEmail().isBlank()){
            throw new InvalidPassengerException("Email cannot be blank!");
        }
        if (createPassengerCommand.getBirthDate() == null || createPassengerCommand.getBirthDate().isAfter(LocalDate.now())){
            throw new InvalidPassengerException("Date of birth cannot be blank!");
        }

        return new Passenger(createPassengerCommand.getName(),
                createPassengerCommand.getSurname(),
                createPassengerCommand.getEmail(),
                createPassengerCommand.getBirthDate());
    }

}
