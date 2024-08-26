package com.francoisgib.project_service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class ErrorObject {
    private final String message;
    private final HttpStatus status;
    private final Timestamp timestamp;

    public ErrorObject(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
