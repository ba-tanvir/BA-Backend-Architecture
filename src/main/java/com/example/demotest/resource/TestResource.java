package com.example.demotest.resource;

import com.example.demotest.dto.StudentDTO;
import com.example.demotest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("test")
public class TestResource {

    @Autowired
    private StudentService  studentService;

    @GetMapping("hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok().body("hello");
    }

    @GetMapping("student")
    public ResponseEntity<List<StudentDTO>> getStudentList() {
        return ResponseEntity.ok().body(studentService.getList());
    }

}
