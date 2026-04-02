package com.quantity.security;

import com.quantity.model.User;
import com.quantity.repository.UserRepository;
import com.quantity.service.JwtService;
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

        String emailTemp = ((OAuth2User) authentication.getPrincipal())
    	        .getAttribute("email");

    	if (emailTemp == null) {
    	    emailTemp = ((OAuth2User) authentication.getPrincipal())
    	            .getAttribute("login") + "@github.com";
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

        // RETURN TOKEN IN RESPONSE
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"token\": \"" + token + "\", \"message\": \"OAuth login successful\"}"
        );
    }
}