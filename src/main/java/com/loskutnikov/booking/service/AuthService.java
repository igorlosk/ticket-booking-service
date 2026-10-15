package com.loskutnikov.booking.service;

import com.loskutnikov.booking.dto.AuthResponse;
import com.loskutnikov.booking.dto.LoginRequest;
import com.loskutnikov.booking.dto.RegisterRequest;
import com.loskutnikov.booking.dto.UserResponse;
import com.loskutnikov.booking.exception.InvalidCredentialsException;
import com.loskutnikov.booking.exception.UsernameAlreadyExistsException;
import com.loskutnikov.booking.model.Role;
import com.loskutnikov.booking.model.User;
import com.loskutnikov.booking.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException(request.username());
        }

        User user = new User(
                null,
                request.username(),
                passwordEncoder.encode(request.password()),
                Role.USER
        );

        User saved = userRepository.save(user);

        return UserResponse.from(saved);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException();
        }

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new InvalidCredentialsException());

        String token = jwtService.generateToken(user);
        return AuthResponse.of(token, user);
    }

}
