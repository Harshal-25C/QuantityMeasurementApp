package com.quantity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantity.dto.AuthResponse;
import com.quantity.dto.LoginRequest;
import com.quantity.dto.RegisterRequest;
import com.quantity.exception.DuplicateEmailException;
import com.quantity.model.User;
import com.quantity.repository.UserRepository;
import com.quantity.service.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

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

        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN
    @PostMapping("/login")
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
    public ResponseEntity<AuthResponse> oauthSuccess(Authentication authentication) {

    	String emailTemp = ((OAuth2User) authentication.getPrincipal())
    	        .getAttribute("email");

    	if (emailTemp == null) {
    	    emailTemp = ((OAuth2User) authentication.getPrincipal())
    	            .getAttribute("login") + "@github.com";
    	}

    	final String email = emailTemp;

        // check if user exists
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