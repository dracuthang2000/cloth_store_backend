package com.ptithcm.clothing_store.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationHandleException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> usernameNotFoundHandleException(UsernameNotFoundException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<Object> passwordWrongHandleException(PasswordWrongException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundHandleException(ResourceNotFoundException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
