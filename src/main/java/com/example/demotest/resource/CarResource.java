package com.example.demotest.resource;


import com.example.demotest.dto.CarDTO;
import com.example.demotest.dto.ResponseData;
import com.example.demotest.entity.Car;
import com.example.demotest.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
@AllArgsConstructor
public class CarResource {

    protected <T> ResponseEntity<?> send(ResponseData<T> responseData) {
        return new ResponseEntity<>(responseData, headerInfo(), HttpStatus.OK);
    }

    private MultiValueMap<String, String> headerInfo() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("app-version", "92");
        return headers;
    }
    private final CarService carService;




    @GetMapping("hello")
    public String hello_cars(){
        return "Hello from Abstract Car Api";
    }

    @GetMapping("get_all")
    public ResponseEntity<List<CarDTO>> get_all_list(){

        //Creating a response header
        HttpHeaders response_header=new HttpHeaders();
        response_header.add("First","This is header.");
        //Calling BaseService
        return ResponseEntity.ok().headers(response_header).body(carService.get_all());
    }

    @PostMapping("create")
        CarDTO create_car(@RequestBody Car new_car){
        return  ResponseEntity.ok().body(carService.create(new_car)).getBody();

    }
    @GetMapping("sort_any_field/{field_name}")
    public ResponseEntity<?>sort_this(@PathVariable("field_name")String field_name) throws NoSuchMethodException {

        return send(new ResponseData<>().success(carService.sort_by_string(field_name)));

    }
}
