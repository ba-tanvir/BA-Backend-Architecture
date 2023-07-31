package com.example.demotest.resource;

import com.example.demotest.entity.Employee;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException (String message, Throwable cause){
        super(message, cause);
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }

    public EmployeeNotFoundException(Throwable cause){
        super(cause);
    }
}
