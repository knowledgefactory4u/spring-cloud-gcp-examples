package com.knf.dev.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;

/** Sample REST Controller to demonstrate Stackdriver Logging. */
@RestController
public class TestController {

    private static final Log LOGGER = LogFactory.getLog(TestController.class);

    @GetMapping("/test")
    public String test() {

        LOGGER.info("Info 1 - Inside test method");
        LOGGER.info("Info 2 - Inside test method");

        return "Testing...";
    }
}
