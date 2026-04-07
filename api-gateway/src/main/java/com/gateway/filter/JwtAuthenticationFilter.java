package com.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

	@Value("${jwt.secret}")
	private String secret;

	public JwtAuthenticationFilter() {
		super(Config.class);
	}	

	// ===== Config class — reads 'secured' from application.yml =====
	public static class Config {
		private boolean secured;

		public boolean isSecured() {
			return secured;
		}

		public void setSecured(boolean secured) {
			this.secured = secured;
		}
	}

	@Override
	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {

			// If route is NOT secured → pass through directly
			if (!config.isSecured()) {
				return chain.filter(exchange);
			}

			// ===== Secured route — validate JWT =====
			String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

			// Check if Authorization header is missing or malformed
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				log.warn("Missing or invalid Authorization header");
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
				byte[] bytes = "{\"error\": \"Missing or invalid Authorization header\"}"
						.getBytes(StandardCharsets.UTF_8);
				var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				return exchange.getResponse().writeWith(Mono.just(buffer));
			}

			// Extract token
			String token = authHeader.substring(7).trim();

			try {
				// Validate token
				Claims claims = extractAllClaims(token);

				// Add username to downstream request headers
				String username = claims.getSubject();
				exchange = exchange.mutate().request(r -> r.header("X-Auth-User", username)).build();

				log.info("JWT validated for user: {}", username);

			} catch (Exception e) {
				log.error("JWT validation failed: {}", e.getMessage());
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
				byte[] bytes = ("{\"error\": \"Invalid or expired token: " + e.getMessage() + "\"}")
						.getBytes(StandardCharsets.UTF_8);
				var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				return exchange.getResponse().writeWith(Mono.just(buffer));
			}

			return chain.filter(exchange);
		};
	}

	// ===== JWT helpers =====

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}