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

        LOGGER.info("This is Info");
        LOGGER.warn("This is Warning");
        LOGGER.error("This is Error");

        return "Testing.....";
    }
}
