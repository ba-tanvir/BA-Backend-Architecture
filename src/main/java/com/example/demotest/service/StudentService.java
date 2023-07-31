package com.example.demotest.service;

import com.example.demotest.dto.StudentDTO;
import com.example.demotest.entity.Student;
import com.example.demotest.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("studentService")
public class StudentService {

    private final StudentRepository repository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> getList() {
        List<Student> students = repository.findAll();
        System.out.println(students);
        return students.stream().map(this::getDto).collect(Collectors.toList());
    }


    private StudentDTO getDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}
