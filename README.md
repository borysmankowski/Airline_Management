# Airline Management

Backend for a flight ticket booking system, built step by step as a learning project.

Stack: Java, Spring Boot, Spring Security (JWT), Maven, TODO: Postgres/MySQL, TODO: Flyway/Liquibase

## What it does

- CRUD for airports, flights and reservations
- Flight search by departure airport, arrival airport and date (direct flights only for now)
- One reservation can include multiple passengers
- Seats are blocked for the duration of a reservation, so two people can't book the same ones
- JWT login with access and refresh tokens
- Roles: ADMIN and NORMAL

## Reservation statuses

A reservation starts as IN_PROGRESS and can then move to one of three states:

- COMPLETED, once it's paid for
- CANCELLED, when it's cancelled
- TIMED_OUT, if nobody confirmed it within the allowed time

Payment is just a plain endpoint for now, eventually it should become a callback from a payment provider.

## Access

- No token needed: login, refresh, account creation
- Adding, editing and deleting flights and airports: ADMIN only
- Flight search: any logged-in user
- Reservations: TODO describe who can see whose

## How to run

```bash
git clone https://github.com/borysmankowski/Airline_Management.git
cd Airline_Management
./mvnw spring-boot:run
```

You need a running database and its connection details set in application.properties. TODO: database name and port.

Tests:

```bash
./mvnw test
```

## Roadmap

Coming soon!