package com.company.controller.exception;

import org.springframework.http.HttpStatus;

public class HTTPException extends RuntimeException {
    private HttpStatus status;

    public HTTPException(HttpStatus status,String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
