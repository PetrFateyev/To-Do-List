package ru.fateyev.ToDoList.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fateyev.ToDoList.util.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotCreatedException.class)
    private ResponseEntity<ErrorResponse> notCreatedException(NotCreatedException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
            ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ValidationErrorResponse(violations),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ValidationErrorResponse(violations), HttpStatus.BAD_REQUEST);
    }
}
