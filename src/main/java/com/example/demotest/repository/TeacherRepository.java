package com.example.demotest.repository;


import com.example.demotest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
