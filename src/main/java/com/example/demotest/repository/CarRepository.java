package com.example.demotest.repository;

import com.example.demotest.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CarRepository extends BaseRepository<Car> {
}
