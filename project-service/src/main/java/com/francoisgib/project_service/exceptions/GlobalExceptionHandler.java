package com.francoisgib.project_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceException.class)
    ResponseEntity<ErrorObject> handleResourceException(ResourceException resourceException) {
        ErrorObject errorObject = new ErrorObject(resourceException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(resourceException.getStatus()).body(errorObject);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorObject> exceptionHandler(Exception exception) {
        ErrorObject errorObject = new ErrorObject(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(errorObject.getStatus()).body(errorObject);
    }
}
