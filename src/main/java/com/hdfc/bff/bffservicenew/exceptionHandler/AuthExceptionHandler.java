package com.hdfc.bff.bffservicenew.exceptionHandler;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthExceptionHandler extends RuntimeException{
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(FeignException ex) {

        HttpStatus status;

        if (ex.status() == 401) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex.status() == 403) {
            status = HttpStatus.FORBIDDEN;
        } else if (ex.status() == 404) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_GATEWAY; // auth-service down or error
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("error", "AUTH_SERVICE_ERROR");
        response.put("message", "Authentication service error");
        response.put("details", ex.getMessage());
        response.put("status", status.value());

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Handles custom AuthServiceException
     */
    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<Map<String, Object>> handleAuthServiceException(
            AuthServiceException ex
    ) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("error", "AUTHENTICATION_FAILED");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.UNAUTHORIZED.value());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    /**
     * Fallback for any other unexpected exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("error", "INTERNAL_ERROR");
        response.put("message", "Something went wrong");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
    }

