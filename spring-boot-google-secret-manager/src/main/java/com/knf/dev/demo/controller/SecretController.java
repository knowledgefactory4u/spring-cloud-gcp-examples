package com.knf.dev.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Value("${sm://knf-secret}")
    String secretMessage;

    @GetMapping("/secret")
    public String getSecretMessage()
    {
        return secretMessage;
    }
}
