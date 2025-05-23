package com.example.auth2fa.controller;

import com.example.auth2fa.dto.LoginRequest;
import com.example.auth2fa.dto.Verify2FARequest;
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

    @Operation(summary = "Login utilisateur")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // TODO : vérifier username/password, générer un token temporaire, envoyer code 2FA
        return ResponseEntity.ok("Login OK, code 2FA envoyé");
    }

    @Operation(summary = "Vérification code 2FA")
    @PostMapping("/verify-2fa")
    public ResponseEntity<String> verify2FA(@RequestBody Verify2FARequest request) {
        // TODO : vérifier le code 2FA
        return ResponseEntity.ok("2FA OK, authentification complète");
    }
}
