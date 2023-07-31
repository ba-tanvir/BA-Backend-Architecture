package com.example.demotest.entity;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="movie")
public class Movie extends BaseEntity {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="release_year")
    private String release_year;

    @Column(name="genre")
    private String genre;

    @Column(name="rating")
    private double rating;

    @Column(name="production_house")
    private String production_house;
}
