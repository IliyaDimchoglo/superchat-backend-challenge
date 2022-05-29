package com.example.superchat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final Integer errorCode;
    private String errorMessage;
}
