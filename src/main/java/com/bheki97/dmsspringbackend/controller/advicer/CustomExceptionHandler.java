package com.bheki97.dmsspringbackend.controller.advicer;

import com.bheki97.dmsspringbackend.exception.DMSException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DMSException.class)
    public String returnMesg(Exception exc){
        return exc.getMessage();
    }
}
