
package com.quantity.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quantity.service.CustomUserDetailsService;
import com.quantity.service.JwtService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		// step 1- read authorization header
		final String authHeader=request.getHeader("Authorization");
		
		//step 2- check if header exists and start with bearer
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// step 3- extract token and remove Bearer prefix
		final String token=authHeader.substring(7).trim();
		
		//step 4- extract username from token
		final String userName=jwtService.extractUsername(token);
		
		//step 5- check if username exists and not already authenticated
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			//step 6- load username from database;
			UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			
			//step 7- validate token
			if(jwtService.isTokenValid(token, userDetails)) {
				
				//step 8- create Authentication object
				UsernamePasswordAuthenticationToken authToken=
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities());
				
				
				//step 9- add request details to auth object
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				
				// step 10- set authentication in security context
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
		
		//step 11- pass to next filter
		filterChain.doFilter(request, response);
	}
}
