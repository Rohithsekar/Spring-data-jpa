package com.spring.SpringDataJPA.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice //Tells springboot that this class is a global exception handler for this application
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RequestEntityAbsentException.class)
    public ResponseEntity<Object> handleRequestEntityAbsentException(RequestEntityAbsentException e){
        return new ResponseEntity<>(new ErrorInfo(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        // Customize the handling of NoHandlerFoundException
        return new ResponseEntity<>(new ErrorInfo("No Handler found", statusCode, LocalDateTime.now()), statusCode);
    }

}
