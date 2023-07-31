package com.example.demotest.dto;

import lombok.Data;

@Data
public class GameDTO implements IRequestDTO {
        private String name;
        private String genre;
        private String release_year;
        private double rating;

}
