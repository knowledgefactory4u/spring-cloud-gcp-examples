package com.knf.dev.demo.repository;

import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import com.google.cloud.spring.data.spanner.repository.query.Query;
import com.knf.dev.demo.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserRepository extends SpannerRepository<User, String> {

    List<User> findByCountry(String country);

    @Query("SELECT * FROM users WHERE name = @name")
    List<User> findByName(@Param("name") String userName);
}
