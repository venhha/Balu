package com.ven.balu.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String phoneNumber;

    public RegisterRequest(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
