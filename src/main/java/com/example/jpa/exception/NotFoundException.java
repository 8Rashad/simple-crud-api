package com.example.jpa.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {

        super(message);
    }
}
