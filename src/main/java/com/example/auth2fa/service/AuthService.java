package com.example.auth2fa.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final Map<String, String> temporaryCodes = new HashMap<>();

    public boolean validateLogin(String username, String password) {
        return "user".equals(username) && "pass".equals(password);
    }

    public String  generate2FACode(String username) {
        String code = String.valueOf((int) (Math.random() * 900000) + 100000); // 6 chiffres
        temporaryCodes.put(username, code);
        System.out.println("Code 2FA pour " + username + " : " + code); // À remplacer par un envoi réel
        return code;
    }

    public boolean verifyCode(String username, String code2fa) {
        return code2fa.equals(temporaryCodes.get(username));
    }

    public String generateJwtToken(String username) {
        return UUID.randomUUID().toString();
    }
}
