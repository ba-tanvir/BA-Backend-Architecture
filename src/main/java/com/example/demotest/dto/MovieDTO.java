package com.example.demotest.dto;


import lombok.Data;

@Data
public class MovieDTO implements IRequestDTO{

    private String name;
    private double rating;
    private String release_year;
    private String production_house;
    private String genre;
}
