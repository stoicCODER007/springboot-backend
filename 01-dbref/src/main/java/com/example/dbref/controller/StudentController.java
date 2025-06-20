package com.example.dbref.controller;

import com.example.dbref.model.Department;
import com.example.dbref.model.Student;
import com.example.dbref.repository.DepartmentRepository;
import com.example.dbref.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Get the actual Department from DB using the ID
        String deptId = student.getDepartment().getId();
        Department dept = departmentRepository.findById(deptId).orElse(null);

        if (dept == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        student.setDepartment(dept);
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
