package com.example.demotest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="game")
public class Game extends BaseEntity{
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="genre")
    private String genre;

    @Column(name="release_year")
    private String release_year;

    @Column(name="rating")
    private double rating;


}
