package com.example.auth2fa.controller;

import com.example.auth2fa.dto.LoginRequest;
import com.example.auth2fa.dto.Verify2FARequest;
import com.example.auth2fa.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentification", description = "Endpoints de login et double authentification")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @Operation(summary = "Login utilisateur")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        if (authService.validateLogin(request.getUsername(), request.getPassword())) {
            authService.generate2FACode(request.getUsername());
            return ResponseEntity.ok("Login OK, code 2FA envoyé");
        } else {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
    }

    @Operation(summary = "Vérification code 2FA")
    @PostMapping("/verify-2fa")
    public ResponseEntity<String> verify2FA(@RequestBody Verify2FARequest request) {
        if (authService.verifyCode(request.getUsername(), request.getCode2fa())) {
            String token = authService.generateJwtToken(request.getUsername());
            return ResponseEntity.ok("2FA OK, authentification complète. Token : " + token);
        } else {
            return ResponseEntity.status(401).body("Code 2FA invalide");
        }
    }
}
