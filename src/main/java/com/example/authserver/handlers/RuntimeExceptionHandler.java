package com.example.authserver.handlers;

import com.example.authserver.exceptions.AccountExistsException;
import com.example.authserver.exceptions.InvalidCredentialsException;
import com.example.authserver.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler({AccountExistsException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(
            RuntimeException e, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<Object> handleUnauthorizedExceptions(
            RuntimeException e, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
