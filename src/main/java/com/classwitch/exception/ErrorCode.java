package com.classwitch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "User email is duplicated."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password.");

    private HttpStatus status;
    private String message;

}