package com.knf.dev.demo.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CartController {

    private static final Logger LOG = LoggerFactory.
            getLogger(CartController.class);
        @Autowired
        RestTemplate restTemplate;

        @GetMapping(value = "/cart")
        public String cart() {

            LOG.info("Inside cart service");

            return "Cart service running...";
        }
    }