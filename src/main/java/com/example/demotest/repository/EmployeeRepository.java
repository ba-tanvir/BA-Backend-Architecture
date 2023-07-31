package com.example.demotest.repository;

import com.example.demotest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Initiates Jpa with database
//This is a mapper between entity class object and database object
@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
