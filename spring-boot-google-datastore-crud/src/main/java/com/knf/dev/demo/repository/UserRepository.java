package com.knf.dev.demo.repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.google.cloud.spring.data.datastore.repository.query.Query;
import com.knf.dev.demo.entity.User;
import java.util.List;

public interface UserRepository extends DatastoreRepository<User, Long> {

    List<User> findByCountry(String country);

    @Query("select * from |com.knf.dev.demo.entity.User| where name = @name")
    List<User> findByName(String name);
}
