package com.example.demotest.dto;


import lombok.Data;

@Data
public class CarDTO implements IRequestDTO {

    private String make;
    private String model;
    private int year;
}

