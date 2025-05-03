package com.e_commerce.e_commerce_demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
