package com.example.superchat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class RestResponseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
