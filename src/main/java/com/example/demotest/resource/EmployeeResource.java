package com.example.demotest.resource;


import com.example.demotest.dto.EmployeeDTO;
import com.example.demotest.entity.Employee;
import com.example.demotest.repository.EmployeeRepository;
import com.example.demotest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Rest controllers
//Takes in Service which contains Repository(database) which contains Entity class
@RestController
@RequestMapping("employee")
public class EmployeeResource {

    @Autowired EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;



    @GetMapping("hello")
    public String getall(){
        return "Hello from Employee API";
    }

    @GetMapping("all")
    public ResponseEntity<List<EmployeeDTO>> All(){
   return ResponseEntity.ok().body(employeeService.getallEmployee());
    }

    //Note: Return type is optional as it may or may not get an Employee object from database
    @GetMapping("single/{id}")
    Optional<Employee> get_one(@PathVariable("id") Integer id){
        if(id>= 10 || id <0){
            throw new EmployeeNotFoundException("Invalid Employee id " + id);
        }
        return employeeRepository.findById(id);

    }



    @PostMapping("create_employee")
     Employee  create_employee(@RequestBody Employee new_emp){
        return ResponseEntity.ok().body(employeeService.save_employee(new_emp)).getBody();
    }

    @PutMapping("update_employee/{id}")
    Employee update_employee(@RequestBody Employee temp_emp, @PathVariable("id") Integer id){

        return employeeRepository.findById(id)
                .map(employee->{
                    employee.setId(temp_emp.getId());
                    employee.setName(temp_emp.getName());
                    return employeeRepository.save(employee);
                }).orElseGet(()->{return employeeRepository.save(temp_emp);
                });

    }

}
