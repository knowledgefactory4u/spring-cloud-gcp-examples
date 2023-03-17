package com.knf.dev.demo.controller;

import com.knf.dev.demo.model.Student;
import com.knf.dev.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("students")
    Flux<Student> getAll() {

        return studentRepository.findAll();
    }

    @GetMapping("students/{id}")
    Mono<Student> getStudent(@PathVariable("id") Long id) {

        return studentRepository.findById(id);
    }

    @PostMapping("students")
    Mono<Student> addStudent(@RequestBody Student student) {

        return studentRepository.save(student);
    }


    @PutMapping("students/{id}")
    private Mono<Student> updateStudent(@PathVariable("id") Long id,
                                  @RequestBody Student student) {

        return studentRepository.findById(id).flatMap(user1 -> {
            student.setId(id);
            return studentRepository.save(student);
        }).switchIfEmpty(Mono.empty());
    }

    @DeleteMapping("students/{id}")
    Mono<Void> deleteById(@PathVariable("id") Long id) {

        return studentRepository.findById(id).flatMap(p ->
                studentRepository.deleteById(p.getId()));
    }
}