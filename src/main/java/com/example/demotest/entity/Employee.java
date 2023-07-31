package com.example.demotest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//Employee entity class creates a table in the database
//@Data is for no setter and getter in the class
@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
