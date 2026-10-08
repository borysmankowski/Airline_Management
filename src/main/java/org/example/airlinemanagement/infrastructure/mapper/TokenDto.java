package org.example.airlinemanagement.infrastructure.mapper;

public record TokenDto(String accessToken, String refreshToken, String tokenType, long expiresIn) {
}
