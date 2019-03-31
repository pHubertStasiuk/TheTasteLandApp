package com.tasteland.app.Tasteland.controller;

import com.tasteland.app.Tasteland.model.ExecutionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            BadCredentialsException.class,
            AuthenticationException.class,
            UsernameNotFoundException.class
    })
    protected ResponseEntity<?> exceptionHandler() {
        return new ResponseEntity<>(new ExecutionStatus("USER_LOGIN_FAILED", "Login Unsuccessful!"), HttpStatus.UNAUTHORIZED);
    }
}
