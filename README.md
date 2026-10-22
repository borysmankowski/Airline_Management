# Airline Management

Backend systemu do rezerwacji biletów lotniczych, robiony etapami jako projekt do nauki.

Stack: Java, Spring Boot, Spring Security (JWT), Maven, TODO: Postgres/MySQL, TODO: Flyway/Liquibase

## Co potrafi

- CRUD dla lotnisk, lotów i rezerwacji
- Szukanie lotów po lotnisku wylotu, przylotu i dacie (na razie tylko loty bezpośrednie)
- Jedna rezerwacja może obejmować kilku pasażerów
- Miejsca są blokowane na czas rezerwacji, więc dwie osoby nie kupią tych samych
- Logowanie przez JWT, z access i refresh tokenem
- Role: ADMIN i NORMAL

## Statusy rezerwacji

Rezerwacja startuje jako IN_PROGRESS i potem może przejść do jednego z trzech stanów:

- COMPLETED, po opłaceniu
- CANCELLED, po anulowaniu
- TIMED_OUT, jeśli nikt jej nie potwierdził w wyznaczonym czasie

Płatność to na razie zwykły endpoint, docelowo ma być callbackiem od operatora płatności.

## Dostępy

- Bez tokena: login, refresh, zakładanie konta
- Dodawanie, edycja i usuwanie lotów oraz lotnisk: tylko ADMIN
- Wyszukiwanie lotów: każdy zalogowany
- Rezerwacje: TODO opisać, kto widzi czyje

## Jak odpalić

```bash
git clone https://github.com/borysmankowski/Airline_Management.git
cd Airline_Management
./mvnw spring-boot:run
```

Trzeba mieć postawioną bazę i wpisane dane do niej w application.properties. TODO: nazwa bazy i port.

Testy:

```bash
./mvnw test
```

## Plan

Coming soon! 
