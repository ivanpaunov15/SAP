package com.ivan.ShopManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyStringException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EmptyStringException(String message) {
        super(message);
    }
}
