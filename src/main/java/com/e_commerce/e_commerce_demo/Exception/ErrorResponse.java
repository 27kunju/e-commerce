package com.e_commerce.e_commerce_demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    String message;

    HttpStatus status;

    LocalDateTime localDate;

}
