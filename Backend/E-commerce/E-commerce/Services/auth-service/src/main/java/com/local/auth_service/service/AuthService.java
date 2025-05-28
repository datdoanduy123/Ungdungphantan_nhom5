package com.local.auth_service.service;

import com.local.auth_service.dto.AuthResponse;
import com.local.auth_service.dto.CustomerRequest;
import com.local.auth_service.dto.LoginRequest;
import com.local.auth_service.dto.RegisterRequest;
import com.local.auth_service.model.User;
import com.local.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.local.auth_service.model.Role;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WebClient.Builder webClientBuilder;

    public AuthResponse register(RegisterRequest request) {
        var user = userRepository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build());

        var customerRequest = new CustomerRequest(user.getId().toString(), request.firstname(), request.lastname(), request.email(), request.address());
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8090/api/v1/customers")
                .bodyValue(customerRequest)
                .retrieve().bodyToMono(Void.class).block();

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponse(jwtService.generateToken(user));
    }
}