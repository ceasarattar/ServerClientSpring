package com.example.springdemojdbc.service;

import com.example.springdemojdbc.dto.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class DecryptorService implements CommandParser {
    private final CommandParser commandParser;
    private final SecretKey secretKey;
    private final IvParameterSpec ivParameterSpec;

    private static final String KEY = "w7jklm8UL0aSdUV3";
    private static final String IV = "ABCDEFGHIJKLMNOP";

    @Autowired
    public DecryptorService(@Lazy CommandParser commandParser) {
        this.commandParser = commandParser;
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(keyBytes, "AES");
        byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);
        this.ivParameterSpec = new IvParameterSpec(ivBytes);
    }

    public String decryptMessage(String encryptedMessage) throws Exception {
        byte[] decodedMessage;
        try {
            decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 input", e);
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] decryptedDecodedMessage = cipher.doFinal(decodedMessage);
        return new String(decryptedDecodedMessage, StandardCharsets.UTF_8);
    }

    @Override
    public EmployeeRequest parseCommand(String finalMessage) {
        try {
            String decryptedMessage = decryptMessage(finalMessage);
            return commandParser.parseCommand(decryptedMessage);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
