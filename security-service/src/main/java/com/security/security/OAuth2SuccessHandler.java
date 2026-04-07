package com.security.security;

import com.security.model.User;
import com.security.repository.UserRepository;
import com.security.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository repository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String emailTemp = oAuth2User.getAttribute("email");

        if (emailTemp == null) {
            emailTemp = oAuth2User.getAttribute("login") + "@github.com";
        }

        final String email = emailTemp;

        User user = repository.findByEmail(email)
                .orElseGet(() -> repository.save(
                        User.builder()
                                .email(email)
                                .name("GitHub User")
                                .mobileNumber("0000000000")
                                .password("oauth")
                                .build()
                ));

        String token = jwtService.generateToken(user);

        response.setContentType("application/json");
        response.getWriter().write(
                "{\"token\": \"" + token + "\", \"message\": \"OAuth login successful\"}"
        );
    }
}