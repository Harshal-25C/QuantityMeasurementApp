package com.security.controller;

import com.security.dto.*;
import com.security.exception.DuplicateEmailException;
import com.security.model.User;
import com.security.repository.UserRepository;
import com.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register, Login and OAuth endpoints")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        if (repository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT token")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Login successful");
    }

    @GetMapping("/oauth-success")
    @Operation(summary = "OAuth2 GitHub login success")
    public ResponseEntity<AuthResponse> oauthSuccess(Authentication authentication) {

        String emailTemp = ((OAuth2User) authentication.getPrincipal())
                .getAttribute("email");

        if (emailTemp == null) {
            emailTemp = ((OAuth2User) authentication.getPrincipal())
                    .getAttribute("login") + "@github.com";
        }

        final String email = emailTemp;

        User user = repository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .name("GitHub User")
                            .mobileNumber("0000000000")
                            .password("oauth")
                            .build();
                    return repository.save(newUser);
                });

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token, "OAuth login successful"));
    }
}