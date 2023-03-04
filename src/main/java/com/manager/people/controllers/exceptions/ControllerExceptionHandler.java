package com.manager.people.controllers.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.manager.people.services.exceptions.ObjectNotFound;

import jakarta.servlet.ServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFound e, ServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e, ServletRequest request) {
        List<FieldError> listFieldError = e.getBindingResult().getFieldErrors();
        String message = listFieldError.stream().map(er -> er.getDefaultMessage()).toList().get(0);

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), message);

        return new ResponseEntity<StandardError>(error, HttpStatus.BAD_REQUEST);
    }

}
