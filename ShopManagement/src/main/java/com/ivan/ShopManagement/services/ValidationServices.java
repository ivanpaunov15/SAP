package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.exceptions.EmptyStringException;
import com.ivan.ShopManagement.exceptions.NegativeNumberException;
import com.ivan.ShopManagement.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServices {

    @Autowired
    public ValidationServices(){

    }

    public void checkIfNegative(long num){
        if(num<0){
            throw new NegativeNumberException("Negative number");
        }
    }

    public void  checkIfStringIsEmpty(String text){
        if(text.isEmpty()){
            throw new EmptyStringException("Text is empty");
        }
    }

    public void  checkIfListSizeIsZero(int num, String text){
        if(num == 0){
            throw new ResourceNotFoundException(text);
        }
    }
}
