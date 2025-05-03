package com.e_commerce.e_commerce_demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String message;

    HttpStatus status;

}
