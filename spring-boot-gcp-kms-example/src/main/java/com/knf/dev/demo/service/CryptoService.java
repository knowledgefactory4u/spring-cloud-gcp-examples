package com.knf.dev.demo.service;

public interface CryptoService {

    String encrypt(String keyId,String text);
    String decrypt(String keyId, String encryptedText);
}
