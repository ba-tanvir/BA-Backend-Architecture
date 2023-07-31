package com.example.demotest.resource;


import com.example.demotest.dto.GameDTO;
import com.example.demotest.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("game")
@RestController
@AllArgsConstructor
public class GameResource {

    private final GameService gameService;

    @GetMapping("get_all")
    public ResponseEntity<List<GameDTO>> get_all(){
        return ResponseEntity.ok().body(gameService.get_all());
    }

    @GetMapping("contains/{name}")
    public ResponseEntity<List<GameDTO>> contains(@PathVariable("name") String name ){
        return ResponseEntity.ok().body(gameService.contains_name(name));
    }

}
