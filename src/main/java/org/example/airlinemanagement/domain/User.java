package org.example.airlinemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Role role;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    private User(String email, String passwordHash, String name, String surname, Role role, ZonedDateTime createdAt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.createdAt = createdAt;
    }

    public static User create(String email, String passwordHash, String name, String surname,
                              Role role, ZonedDateTime createdAt) {
        if (email == null || email.isBlank()) throw new ValidationException("Email is required");
        if (name == null || name.isBlank()) throw new ValidationException("Name is required");
        if (surname == null || surname.isBlank()) throw new ValidationException("Surname is required");
        return new User(email.trim().toLowerCase(), passwordHash, name.trim(), surname.trim(), role, createdAt);
    }

    public void updateProfile(String name, String surname) {
        if (name == null || name.isBlank()) throw new ValidationException("Name is required");
        if (surname == null || surname.isBlank()) throw new ValidationException("Surname is required");
        this.name = name.trim();
        this.surname = surname.trim();
    }

    public void changeRole(Role role) {
        this.role = role;
    }
}