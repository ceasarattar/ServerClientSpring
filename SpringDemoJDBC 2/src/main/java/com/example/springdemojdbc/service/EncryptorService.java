package com.example.springdemojdbc.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncryptorService {
    private final SecretKey secretKey;
    private final IvParameterSpec ivParameterSpec;

    private static final String KEY = "w7jklm8UL0aSdUV3";
    private static final String IV = "ABCDEFGHIJKLMNOP";

    public EncryptorService() {
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(keyBytes, "AES");
        byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);
        this.ivParameterSpec = new IvParameterSpec(ivBytes);
    }

    public String encryptMessage(String message) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encryptedMessage = cipher.doFinal(messageBytes);
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }
}
