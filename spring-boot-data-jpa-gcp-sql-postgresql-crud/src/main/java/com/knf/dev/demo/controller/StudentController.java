package com.knf.dev.demo.controller;

import com.knf.dev.demo.entity.Student;
import com.knf.dev.demo.exception.ResourceNotFoundException;
import com.knf.dev.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    // create student rest API
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student)  {

        return studentRepository.save(student);

    }

    // get student by id rest api
    @GetMapping("/students/{id}")
    public Student findStudentById(@PathVariable Long id) {

        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException
                  ("Student not exist with id :" + id));
        return student;
    }

   // update student rest api
   @PutMapping("/students/{id}")
   public Student updateStudent(@PathVariable Long id,
                           @RequestBody Student studentDetails) {

       Student student = studentRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException
                       ("Student not exist with id :" + id));

       student.setFirstName(studentDetails.getFirstName());
       student.setLastName(studentDetails.getLastName());
       student.setEmail(studentDetails.getEmail());

       return studentRepository.save(student);
    }

    // delete student rest api
    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent
               (@PathVariable Long id) {

        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException
                        ("Student not exist with id :" + id));


        studentRepository.delete(student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
