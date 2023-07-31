package com.example.demotest.resource;


import com.example.demotest.dto.TeacherDTO;
import com.example.demotest.entity.Teacher;
import com.example.demotest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")

public class TeacherResource {

    @Autowired
    TeacherService teacherService;

    @PostMapping("create_teacher")
    Teacher create_teacher(@RequestBody Teacher new_teacher){
        return ResponseEntity.ok().body(teacherService.create_teacher(new_teacher)).getBody();
    }

    @GetMapping("get_all")
    public ResponseEntity <List<TeacherDTO>> get_all(){
        return ResponseEntity.ok().body(teacherService.get_teacher_list());
    }
    @GetMapping("get_name/{name}")
    public ResponseEntity<List<TeacherDTO>> getByName(@PathVariable("name") String name){

        return ResponseEntity.ok().body(teacherService.getByName(name));
    }

    @GetMapping("get_by_designation/{designation}")
    public ResponseEntity<List<TeacherDTO>> get_designation(@PathVariable("designation") String designation){
        return ResponseEntity.ok().body(teacherService.get_by_designation(designation));
    }

}
