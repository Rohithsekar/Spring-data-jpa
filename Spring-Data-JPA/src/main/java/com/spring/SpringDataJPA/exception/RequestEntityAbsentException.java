package com.spring.SpringDataJPA.exception;

public class RequestEntityAbsentException extends RuntimeException {
    public RequestEntityAbsentException(String message){
        super(message);
    }

}
