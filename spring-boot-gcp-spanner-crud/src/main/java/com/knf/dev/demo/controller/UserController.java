package com.knf.dev.demo.controller;

import com.google.common.collect.Lists;
import com.knf.dev.demo.entity.User;
import com.knf.dev.demo.exception.ResourceNotFoundException;
import com.knf.dev.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // get all users rest API
    @GetMapping("/users")
    public List<User> getAllUsers() {

        return Lists.newArrayList(userRepository.findAll());
    }

    // get all users by name rest API
    @GetMapping("/users/name/{name}")
    public List<User> getAllUsersByName(@PathVariable String name) {

        return userRepository.findByName(name);
    }

    // get all users by country rest API
    @GetMapping("/users/country/{name}")
    public List<User> getAllUsersByCountry(@PathVariable String name) {

        return userRepository.findByCountry(name);
    }

    // create user rest API
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        //Random UUID
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    // get user by id rest api
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }

    // update user rest api
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id,
                                           @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not exist with id :" + id));
        user.setCountry(userDetails.getCountry());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete user rest api
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser
           (@PathVariable String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not exist with id :" + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}