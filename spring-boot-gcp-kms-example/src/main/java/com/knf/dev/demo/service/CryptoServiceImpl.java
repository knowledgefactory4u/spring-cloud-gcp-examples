package com.knf.dev.demo.service;

import com.google.cloud.spring.kms.KmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CryptoServiceImpl implements CryptoService{

    @Autowired
    private KmsTemplate kmsTemplate;

    @Override
    public String encrypt(String keyId,String text) {

        byte[] encryptedBytes = kmsTemplate.
                encryptText(keyId, text);
        String encryptedText = encodeBase64(encryptedBytes);
        return encryptedText;
    }

    @Override
    public String decrypt(String keyId, String encryptedText) {

        byte[] decryptedBytes = decodeBase64(encryptedText);
        String decrypted = kmsTemplate.
                decryptText(keyId, decryptedBytes);

        return decrypted;
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
