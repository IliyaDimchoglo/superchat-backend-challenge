package com.example.superchat.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getName() + " type mismatch"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorResponseList.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error.getField() + " " + error.getDefaultMessage()));
        }
        return new ResponseEntity<>(errorResponseList, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestResponseException.class)
    public ResponseEntity<ErrorResponse> handleRestResponseException(RestResponseException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getHttpStatus().value(), e.getMessage()), new HttpHeaders(), e.getHttpStatus());
    }
}