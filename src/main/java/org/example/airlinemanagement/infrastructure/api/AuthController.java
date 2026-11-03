package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.AuthService;
import org.example.airlinemanagement.application.commands.LoginCommand;
import org.example.airlinemanagement.application.commands.RefreshTokenCommand;
import org.example.airlinemanagement.infrastructure.mapper.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid LoginCommand command) {
        return authService.login(command);
    }

    @PostMapping("/refresh")
    public TokenDto refresh(@RequestBody @Valid RefreshTokenCommand command) {
        return authService.refresh(command);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody @Valid RefreshTokenCommand command) {
        authService.logout(command);
    }
}
