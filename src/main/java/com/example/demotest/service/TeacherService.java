package com.example.demotest.service;


import com.example.demotest.dto.TeacherDTO;
import com.example.demotest.entity.Teacher;
import com.example.demotest.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("teacherService")
public class TeacherService {

    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;

    private TeacherDTO getDto(Teacher temp_teacher){
        return modelMapper.map(temp_teacher,TeacherDTO.class);
    }

    public Teacher create_teacher(Teacher temp_teacher){
        return teacherRepository.save(temp_teacher);
    }

    public List<TeacherDTO> get_teacher_list(){
        List<Teacher> new_list=teacherRepository.findAll();
        return new_list.stream().map(this::getDto).collect(Collectors.toList());
    }
    public List<TeacherDTO> getByName(String name){
        List <Teacher> teacher_list=teacherRepository.findAll();
        List <Teacher> return_list=new ArrayList<>();

        for(Teacher teacher:teacher_list){
            if(teacher.getName().contains(name)){
                return_list.add(teacher);
            }
        }
        return return_list.stream().map(this::getDto).collect(Collectors.toList());
    }

    //This api call has to work with case insensitivity
    //trailing whitespaces
    public List<TeacherDTO> get_by_designation(String designation){
        List<Teacher> new_list=teacherRepository.findAll();
        List<Teacher> return_list=new ArrayList<>();
        for(Teacher teacher:new_list){

            String final_designation=trim_function(designation);
            final_designation=case_insensitivity(final_designation);

            if(teacher.getDesignation().toLowerCase().equals(final_designation)){
                return_list.add(teacher);
            }
        }
        return return_list.stream().map(this::getDto).collect(Collectors.toList());
    }


    //Adding Business logic functions for improving user experience
    public String trim_function(String param){
        return param.trim();
    }

    public String case_insensitivity(String param){
        return param.toLowerCase();
    }

}
