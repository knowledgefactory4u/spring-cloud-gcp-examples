package com.knf.dev.demo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CustomerController {

    private static final Logger LOG = LoggerFactory.
            getLogger(CustomerController.class);

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/customer")
    public String customer() {

        LOG.info("Inside customer service");

        String responseFromProductService = restTemplate.
                getForObject("http://localhost:9080/product",
                        String.class);
        LOG.info("Successfully communicated " +
                "with product service");

        return responseFromProductService+" -> Customer Service running!";
    }
}
