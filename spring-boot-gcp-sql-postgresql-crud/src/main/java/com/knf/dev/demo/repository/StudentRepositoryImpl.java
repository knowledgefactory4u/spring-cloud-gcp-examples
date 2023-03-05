package com.knf.dev.demo.repository;

import com.knf.dev.demo.exception.ResourceNotFoundException;
import com.knf.dev.demo.model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student findById(Long id) {

        String sqlQuery = Query.FIND_ONE;
        Student student = null;
        try {
            student = jdbcTemplate.
                    queryForObject(sqlQuery,
                            this::mapRowToStudent, id);
        }catch(EmptyResultDataAccessException e)
        {
           throw new ResourceNotFoundException
                   ("Student not exist with id :" + id);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {

        String sqlQuery = Query.FIND_ALL;

        return jdbcTemplate.
                query(sqlQuery, this::mapRowToStudent);
    }

    @Override
    public int insert(Student student) {

        String sqlQuery = Query.SAVE;

        return jdbcTemplate.update(sqlQuery,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail());
    }

    @Override
    public int update(Student student) {

        String sqlQuery = Query.UPDATE;

        return jdbcTemplate.update(sqlQuery,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getId());
    }

    @Override
    public int deleteById(Long id) {

        String sqlQuery = Query.DELETE;

        return jdbcTemplate.update(sqlQuery, id);
    }

    private Student mapRowToStudent(ResultSet resultSet, int rowNum)
            throws SQLException {

        Student student = new Student(resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"));

        return student;
    }
}