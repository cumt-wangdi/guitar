package com.example.guitar.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyException {

    @ExceptionHandler(Exception.class)
    public String handleException(){
        return "error";
    }

}
