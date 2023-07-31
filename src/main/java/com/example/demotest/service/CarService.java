package com.example.demotest.service;


import com.example.demotest.dto.CarDTO;
import com.example.demotest.entity.Car;
import com.example.demotest.repository.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("carService")
public class CarService extends BaseService<Car, CarDTO> {

    public CarService(BaseRepository<Car> repository, ModelMapper modelMapper){
        super(repository,modelMapper);
    }
}
