package com.example.civiccomplaints.dto;

public class LoginRequest {
    private String mobile;
    private String passwordHash;

    // getters + setters
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}