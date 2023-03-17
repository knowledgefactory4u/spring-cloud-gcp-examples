package com.knf.dev.demo.repository;

import com.knf.dev.demo.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository
        extends ReactiveCrudRepository<Student, Long> {
}
