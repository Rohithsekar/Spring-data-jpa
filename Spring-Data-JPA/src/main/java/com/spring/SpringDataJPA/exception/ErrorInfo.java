package com.spring.SpringDataJPA.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class ErrorInfo {
    private String message;
    private HttpStatusCode statusCode;
    private LocalDateTime timestamp;

    public ErrorInfo(String message, HttpStatusCode statusCode, LocalDateTime timestamp) {
        this.message = message;
        this.statusCode = statusCode; // Obtain the numeric value of HttpStatus.NOT_FOUND
        this.timestamp = timestamp;
    }

    }


