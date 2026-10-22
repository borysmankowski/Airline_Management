package org.example.airlinemanagement.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.UserService;
import org.example.airlinemanagement.application.commands.ChangeRoleCommand;
import org.example.airlinemanagement.application.commands.create.CreateUserCommand;
import org.example.airlinemanagement.application.commands.update.UpdateUserCommand;
import org.example.airlinemanagement.infrastructure.mapper.UserDto;
import org.example.airlinemanagement.security.Caller;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid CreateUserCommand command) {
        return userService.createUser(command);
    }

    @GetMapping("/me")
    public UserDto me(Authentication auth) {
        return userService.getMe(Caller.from(auth));
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id, Authentication auth) {
        return userService.getUser(id, Caller.from(auth));
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody @Valid UpdateUserCommand command,
                              Authentication auth) {
        return userService.updateUser(id, command, Caller.from(auth));
    }

    @PatchMapping("/{id}/role")
    public UserDto changeRole(@PathVariable int id, @RequestBody @Valid ChangeRoleCommand command) {
        return userService.changeRole(id, command.getRole());
    }
}
