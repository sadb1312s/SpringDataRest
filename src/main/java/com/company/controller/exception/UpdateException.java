package com.company.controller.exception;

import org.springframework.http.HttpStatus;

public class UpdateException extends HTTPException {

    public UpdateException(HttpStatus status, String message) {
        super(status, message);
    }
}


