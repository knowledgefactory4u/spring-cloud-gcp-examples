package com.knf.dev.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private PubsubOutboundGateway messagingGateway;

    @PostMapping("/postMessage")
    public String publishMessage(@RequestParam("message") String message) {

        messagingGateway.sendToPubsub(message);
        return "Message was sent successfully";
    }

}