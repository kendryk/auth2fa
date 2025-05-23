package com.example.auth2fa.dto;

import jakarta.validation.constraints.NotBlank;

public class Verify2FARequest {

    @NotBlank
    private String username;

    @NotBlank
    private String code2fa;

    // getters & setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCode2fa() { return code2fa; }
    public void setCode2fa(String code2fa) { this.code2fa = code2fa; }

}
