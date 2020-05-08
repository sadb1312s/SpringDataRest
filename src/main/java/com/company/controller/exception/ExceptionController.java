package com.company.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNoContentException (NotFoundException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity handleUpdateException (UpdateException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
