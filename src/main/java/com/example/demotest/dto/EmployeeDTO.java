package com.example.demotest.dto;


import lombok.Data;

//DTO = Data Transfer Object. is actually a bean.
//Bean just sends data in json format.
//DTO is used for hiding away variables from user.
//Employee has two different variables, among them only name will be shown and not id.

@Data
public class EmployeeDTO{
    private String name;
}
