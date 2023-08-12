package com.example.jpa.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionNotifiers {
    UNEXPECTED_ERROR("Unexpected error occur"),
    USER_NOT_FOUND("User not found");

    private final String message;
}
