package com.knf.dev.demo.controller;

import com.knf.dev.demo.model.Student;
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
    public Map<String, Boolean> createStudent(@RequestBody Student student)  {

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = studentRepository.insert(student) > 0 ?
                response.put("created", Boolean.TRUE) :
                response.put("created", Boolean.FALSE);

        return response;

    }

    // get student by id rest api
    @GetMapping("/students/{id}")
    public Student findStudentById(@PathVariable Long id) {

        Student student = studentRepository.findById(id);
        return student;
    }

   // update student rest api
   @PutMapping("/students/{id}")
   public Map<String, Boolean> updateStudent(@PathVariable Long id,
                                             @RequestBody Student studentDetails) {

       studentDetails.setId(id);
       Map<String, Boolean> response = new HashMap<>();

       Boolean bool = studentRepository.update(studentDetails) > 0 ?
               response.put("updated", Boolean.TRUE) :
               response.put("updated", Boolean.FALSE);

      return response;
    }

    // delete student rest api
    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent
               (@PathVariable Long id) {

        Map<String, Boolean> response = new HashMap<>();

        Boolean bool = studentRepository.deleteById(id) > 0 ?
                response.put("deleted", Boolean.TRUE) :
                response.put("deleted", Boolean.FALSE);
        return response;
    }
}