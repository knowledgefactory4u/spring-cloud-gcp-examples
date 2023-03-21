package com.knf.dev.demo.controller;

import com.google.cloud.spring.kms.KmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KmsController {

    @Autowired
    private KmsTemplate kmsTemplate;

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String keyId,
                          @RequestParam String text) {

        byte[] encryptedBytes = kmsTemplate.
                encryptText(keyId, text);
        String encryptedText = encodeBase64(encryptedBytes);

        return "Encrypted Text= "+encryptedText;
    }

    @PostMapping("/decrypt")
    public String decrypt(
            @RequestParam String keyId,
            @RequestParam String encryptedText) {

        byte[] decryptedBytes = decodeBase64(encryptedText);
        String decrypted = kmsTemplate.decryptText(keyId, decryptedBytes);

        return "Decrypted Text= "+decrypted;
    }

    private String encodeBase64(byte[] bytes) {
        byte[] encoded = Base64.getEncoder().encode(bytes);
        return new String(encoded);
    }

    private byte[] decodeBase64(String encryptedText) {
        byte[] bytes = encryptedText.getBytes();
        return Base64.getDecoder().decode(bytes);
    }
}
