package com.zephyr_jarvis.transport_issue_reporting_platform.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Spring calls this method whenever a controller or service throws ValidationException.
    @ExceptionHandler(ValidationException.class)
    // The response will use HTTP 400 instead of the default HTTP 500 server error.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    // This becomes the JSON response body, for example: {"message":"Passwords do not match"}.
    public record ErrorResponse(String message) {
    }
}
