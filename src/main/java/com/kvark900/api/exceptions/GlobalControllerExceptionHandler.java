package com.kvark900.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Error> handleAuthenticationException(HttpServletRequest request, AuthenticationException e) {
        Error error = new Error(new Date(), e.getMessage(), request.getRequestURI());
        if (e instanceof BadCredentialsException)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.status(HttpStatus.BAD_REQUEST.toString()));
        else if (e instanceof DisabledException)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error.status(HttpStatus.FORBIDDEN.toString()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.status(HttpStatus.UNAUTHORIZED.toString()));
    }
}
