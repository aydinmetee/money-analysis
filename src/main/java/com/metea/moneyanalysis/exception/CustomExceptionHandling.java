package com.metea.moneyanalysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceExecutionException.class)
    public ResponseEntity<Object> handleExceptions(ServiceExecutionException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(LoginExecutionException.class)
    public ResponseEntity<Object> handleExceptions(LoginExecutionException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
