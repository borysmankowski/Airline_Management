package org.example.airlinemanagement.infrastructure;

import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.UserService;
import org.example.airlinemanagement.application.commands.create.CreateUserCommand;
import org.example.airlinemanagement.domain.Role;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminInitializer implements ApplicationRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${airline.admin.email:}")
    private String email;
    @Value("${airline.admin.password:}")
    private String password;

    @Override
    public void run(ApplicationArguments args) {
        if (email.isBlank() || password.isBlank() || userRepository.existsByEmail(email.toLowerCase())) {
            return;
        }
        CreateUserCommand command = new CreateUserCommand();
        command.setEmail(email);
        command.setPassword(password);
        command.setName("Admin");
        command.setSurname("Admin");
        userService.createWithRole(command, Role.ADMIN);
    }
}
