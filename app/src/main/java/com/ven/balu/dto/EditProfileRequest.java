package com.ven.balu.dto;

public class EditProfileRequest {
    private int customerId;
    private String firstName;
    private String phoneNumber;
    private String email;
    private String address;
    private String dob;
    private String gender;

    public EditProfileRequest(int customerId, String firstName, String phoneNumber, String email, String address, String dob, String gender) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }
}
