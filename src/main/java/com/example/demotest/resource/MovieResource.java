package com.example.demotest.resource;


import com.example.demotest.dto.MovieDTO;
import com.example.demotest.dto.ResponseData;
import com.example.demotest.entity.Movie;
import com.example.demotest.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

import static java.lang.Double.isNaN;

@RestController
@RequestMapping("movie")
@AllArgsConstructor
public class MovieResource {
    private final MovieService movieService;

    protected <T> ResponseEntity<?> send(ResponseData<T> responseData) {
        return new ResponseEntity<>(responseData, headerInfo(), HttpStatus.OK);
    }

    private MultiValueMap<String, String> headerInfo() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("app-version", "92");
        return headers;
    }
    @GetMapping("get-all")
    public ResponseEntity<List<MovieDTO>> get_all_function(){
       // return ResponseEntity.ok().body(movieService.get_all());
        return send(new ResponseData<>().success(movieService.get_all()));
    }

    @PostMapping("create_movie")
    public ResponseEntity<?> create_car(@RequestBody Movie new_movie){
        if(requiredParameters(new_movie)){
            System.out.println("This ParamObject is legit "+requiredParameters(new_movie));

            movieService.trimStringValues(new_movie);
            return send(new ResponseData<>().success(movieService.create(new_movie)));
        }else{
            System.out.println(requiredParameters(new_movie));
            return send(new ResponseData<>().failed("Request Failed not valid arguments", 303));
        }
    }

    @GetMapping("contains_name/{name}")
    public ResponseEntity<?> conatins_name(@PathVariable("name") String name){
        System.out.println("Contains_name is working");
        return send(new ResponseData<>().success(movieService.contains_name(name)));
    }

    //Sort based on rating
    @GetMapping("sort/{field_name}")
    public ResponseEntity<?>rate_this(@PathVariable("field_name")String field_name){
        return send(new ResponseData<>().success(movieService.sort(field_name)));
    }
    //Sort based on released_year

    @GetMapping("sort_any_field/{field_name}")
    public ResponseEntity<?>sort_this(@PathVariable("field_name")String field_name) throws NoSuchMethodException {

        return send(new ResponseData<>().success(movieService.sort_by_string(field_name)));

    }

    public boolean requiredParameters(Movie paramObject) {
        System.out.println("This is paramobject from the function-----");
        System.out.println(paramObject);
        if (paramObject.getName()!="" &&
                ! isNaN(paramObject.getId()) &&
                paramObject.getGenre()!=""
            ) {
            return true;
        }else{
            return false;
        }
    }




}

