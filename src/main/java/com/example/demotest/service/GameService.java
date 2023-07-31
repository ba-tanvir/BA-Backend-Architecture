package com.example.demotest.service;

import com.example.demotest.dto.GameDTO;
import com.example.demotest.dto.IRequestDTO;
import com.example.demotest.entity.BaseEntity;
import com.example.demotest.entity.Game;
import com.example.demotest.repository.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("gameService")
public class GameService extends BaseService<Game, GameDTO>{

    public GameService(BaseRepository<Game> repository, ModelMapper modelMapper){
        super(repository,modelMapper);
    }

}
