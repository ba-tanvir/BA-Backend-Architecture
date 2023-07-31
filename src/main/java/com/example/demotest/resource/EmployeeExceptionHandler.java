package com.example.demotest.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class EmployeeExceptionHandler {

    //Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc){

        //Create a Employee Error response
        EmployeeErrorResponse err=new EmployeeErrorResponse();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(exc.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        //Return response entity

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);

    }

    //Add a generic exception handler
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exception){

        //Create a generic error message
        EmployeeErrorResponse error=new EmployeeErrorResponse();

        //Setting the values
        error.setMessage("Expecting a number not a String");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
