package com.quantity.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(QuantityMeasurementException.class)
	public ResponseEntity<Map<String, Object>> handleQuantityException(QuantityMeasurementException ex,
			HttpServletRequest request) {
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now().toString());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", "Quantity Measurement Error");
		error.put("message", ex.getMessage());
		error.put("path", request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(f -> f.getField() + ": " + f.getDefaultMessage()).findFirst().orElse("Validation failed");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message(message)
				.statusCode(HttpStatus.BAD_REQUEST.value()).timestamp(LocalDateTime.now()).build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorResponse.builder().message("Unexpected error: " + ex.getMessage())
						.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(LocalDateTime.now()).build());
	}
}