package com.security.exception;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
	private String message;
	private int statusCode;
	private LocalDateTime timestamp;
}