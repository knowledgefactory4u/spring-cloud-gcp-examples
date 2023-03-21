package com.knf.dev.demo.controller;

import com.knf.dev.demo.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KmsController {

    @Autowired
    private CryptoService cryptoService;

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String keyId,
                          @RequestParam String text) {


        return "Encrypted Text= " +
                cryptoService.encrypt(keyId,text);
    }

    @PostMapping("/decrypt")
    public String decrypt(
            @RequestParam String keyId,
            @RequestParam String encryptedText) {

        return "Decrypted Text= " +
                cryptoService.decrypt(keyId,encryptedText);
    }
}
