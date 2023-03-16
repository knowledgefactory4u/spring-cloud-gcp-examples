package com.knf.dev.demo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AccountController {

    private static final Logger LOG = LoggerFactory.
            getLogger(AccountController.class);
        @Autowired
        RestTemplate restTemplate;

        @GetMapping(value = "/account")
        public String account() {

            LOG.info("Inside account service");
            String responseFromCartService = restTemplate.
                   getForObject("http://localhost:9080/cart",
                    String.class);
            LOG.info("Successfully communicated " +
               "with cart service");

            return responseFromCartService+" and Account Service up...";
        }
    }
