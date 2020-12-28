package com.ivan.ShopManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotEnoughProductsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotEnoughProductsException(String message) {
        super(message);
    }
}
