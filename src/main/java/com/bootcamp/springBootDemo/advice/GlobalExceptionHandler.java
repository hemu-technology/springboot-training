package com.bootcamp.springBootDemo.advice;

import com.bootcamp.springBootDemo.exception.InactiveEmployeeException;
import com.bootcamp.springBootDemo.exception.InvalidEmployeeException;
import com.bootcamp.springBootDemo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "Resource not found.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InactiveEmployeeException.class)
    public ResponseEntity<?> handleInactiveEmployeeException(InactiveEmployeeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", "The employee has left the company and cannot be modified.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    public ResponseEntity<?> handleInvalidEmployeeException(InvalidEmployeeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", "The employee information is invalid.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "Internal system error.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
