package com.company.service.updatetable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UpdateException extends RuntimeException {
    public UpdateException(String message){
        super(message);
    }
}


