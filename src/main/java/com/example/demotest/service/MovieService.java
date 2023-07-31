package com.example.demotest.service;

import com.example.demotest.dto.MovieDTO;
import com.example.demotest.entity.Movie;
import com.example.demotest.repository.BaseRepository;
import com.example.demotest.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service("movieService")
public class MovieService extends BaseService<Movie, MovieDTO>{

    public MovieService(BaseRepository<Movie> movieRepository, ModelMapper modelMapper){
        super(movieRepository,modelMapper);
    }

}
