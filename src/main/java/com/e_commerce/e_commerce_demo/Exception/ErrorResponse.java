package com.e_commerce.e_commerce_demo.Exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ErrorResponse {

    String message;

    HttpStatus status;

    LocalDateTime localDate;

    public ErrorResponse( String message, HttpStatus status, LocalDateTime localDate) {

        this.message = message;
        this.status = status;
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
