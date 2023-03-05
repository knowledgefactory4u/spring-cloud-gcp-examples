package com.knf.dev.demo.repository;

import com.knf.dev.demo.model.Student;
import java.util.List;

public interface StudentRepository {

    public Student findById(Long id);

    public List<Student> findAll();

    public int insert(Student student);

    public int update(Student student);

    public int deleteById(Long id);
}