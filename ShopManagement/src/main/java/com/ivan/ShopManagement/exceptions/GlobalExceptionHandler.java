package com.ivan.ShopManagement.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughProductsException.class)
    public ResponseEntity<?> notEnoughProductHandling(NotEnoughProductsException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegativeNumberException.class)
    public ResponseEntity<?> negativeNumberHandling(NegativeNumberException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyStringException.class)
    public ResponseEntity<?> emptyStringrHandling(EmptyStringException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> ValidationErrorHandling(MethodArgumentNotValidException exception){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation Error");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}