package com.example.demotest.service;

import java.util.List;

import com.example.demotest.dto.EmployeeDTO;
import com.example.demotest.entity.Employee;
import com.example.demotest.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//EmployeeService contains Business logic for this class
//Takes in the database repository and makes changes to it
@AllArgsConstructor
@Service("employeeService")
public class EmployeeService {


    private final ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getallEmployee(){
        List<Employee> employee_list=employeeRepository.findAll();
        return  employee_list.stream().map(this::getDto).collect(Collectors.toList());
    }

    public Employee save_employee(Employee temp_emp){
        return employeeRepository.save(temp_emp);
    }

    private EmployeeDTO getDto(Employee employee){
        return modelMapper.map(employee,EmployeeDTO.class);
    }


}
