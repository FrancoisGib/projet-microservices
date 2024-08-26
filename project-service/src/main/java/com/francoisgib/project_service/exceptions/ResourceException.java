package com.francoisgib.project_service.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceException extends Exception {
    private final HttpStatus status;

    public ResourceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
