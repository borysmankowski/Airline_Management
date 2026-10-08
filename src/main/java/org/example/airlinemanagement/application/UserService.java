package org.example.airlinemanagement.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.airlinemanagement.application.commands.create.CreateUserCommand;
import org.example.airlinemanagement.application.commands.update.UpdateUserCommand;
import org.example.airlinemanagement.domain.ConflictException;
import org.example.airlinemanagement.domain.Role;
import org.example.airlinemanagement.domain.User;
import org.example.airlinemanagement.infrastructure.mapper.UserDto;
import org.example.airlinemanagement.infrastructure.mapper.UserMapper;
import org.example.airlinemanagement.infrastructure.repository.UserRepository;
import org.example.airlinemanagement.security.Caller;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final Clock clock;

    /** Public registration - always role NORMAL. */
    @Transactional
    public UserDto createUser(CreateUserCommand command) {
        return userMapper.toDto(createWithRole(command, Role.NORMAL));
    }

    @Transactional
    public User createWithRole(CreateUserCommand command, Role role) {
        String email = command.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("Email already registered");
        }
        User user = User.create(email, passwordEncoder.encode(command.getPassword()),
                command.getName(), command.getSurname(), role, ZonedDateTime.now(clock));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDto getMe(Caller caller) {
        return userMapper.toDto(userRepository.findByEmail(caller.email())
                .orElseThrow(() -> new EntityNotFoundException("User " + caller.email() + " not found")));
    }

    @Transactional(readOnly = true)
    public UserDto getUser(int id, Caller caller) {
        User user = find(id);
        assertSelfOrAdmin(user, caller);
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Transactional
    public UserDto updateUser(int id, UpdateUserCommand command, Caller caller) {
        User user = find(id);
        assertSelfOrAdmin(user, caller);
        user.updateProfile(command.getName(), command.getSurname());
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto changeRole(int id, Role role) {
        User user = find(id);
        user.changeRole(role);
        return userMapper.toDto(user);
    }

    private User find(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
    }

    private void assertSelfOrAdmin(User target, Caller caller) {
        if (!caller.admin() && !target.getEmail().equals(caller.email())) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
