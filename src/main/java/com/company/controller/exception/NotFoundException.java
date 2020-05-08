package com.company.controller.exception;

import org.springframework.http.HttpStatus;


public class NotFoundException extends HTTPException {
    private static final String defaultMessage = "object with given id not found";
    private static final HttpStatus defaultStatus = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(defaultStatus,defaultMessage);
    }
}
