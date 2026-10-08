package org.example.airlinemanagement.application;

import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.commands.LoginCommand;
import org.example.airlinemanagement.application.commands.RefreshTokenCommand;
import org.example.airlinemanagement.domain.UnauthorizedException;
import org.example.airlinemanagement.domain.User;
import org.example.airlinemanagement.infrastructure.mapper.TokenDto;
import org.example.airlinemanagement.infrastructure.repository.RefreshTokenRepository;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.example.airlinemanagement.security.JwtProperties;
import org.example.airlinemanagement.security.RefreshToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String BAD_CREDENTIALS = "Invalid email or password";
    private static final String BAD_REFRESH = "Invalid refresh token";

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final JwtProperties jwtProperties;
    private final Clock clock;

    @Transactional
    public TokenDto login(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail().trim().toLowerCase())
                .filter(User::isEnabled)
                .orElseThrow(() -> new UnauthorizedException(BAD_CREDENTIALS));
        if (!passwordEncoder.matches(command.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException(BAD_CREDENTIALS);   // ten sam komunikat: brak enumeracji kont
        }
        return issueTokens(user);
    }

    @Transactional(noRollbackFor = UnauthorizedException.class)
    public TokenDto refresh(RefreshTokenCommand command) {
        RefreshToken stored = refreshTokenRepository
                .findByTokenHash(TokenService.hash(command.getRefreshToken()))
                .orElseThrow(() -> new UnauthorizedException(BAD_REFRESH));

        if (stored.isRevoked()) {
            refreshTokenRepository.revokeAllForUser(stored.getUser().getId());
            throw new UnauthorizedException(BAD_REFRESH);
        }
        if (stored.isExpired(ZonedDateTime.now(clock)) || !stored.getUser().isEnabled()) {
            throw new UnauthorizedException(BAD_REFRESH);
        }
        stored.revoke();
        return issueTokens(stored.getUser());
    }

    @Transactional
    public void logout(RefreshTokenCommand command) {
        refreshTokenRepository.findByTokenHash(TokenService.hash(command.getRefreshToken()))
                .ifPresent(RefreshToken::revoke);
    }

    private TokenDto issueTokens(User user) {
        String refreshValue = tokenService.newRefreshTokenValue();
        refreshTokenRepository.save(RefreshToken.issue(
                TokenService.hash(refreshValue), user,
                ZonedDateTime.now(clock).plus(jwtProperties.refreshTokenTtl())));
        return new TokenDto(tokenService.createAccessToken(user), refreshValue, "Bearer",
                jwtProperties.accessTokenTtl().toSeconds());
    }
}