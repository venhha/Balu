package com.ven.balu.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Customer2 {
    @SerializedName("customerId")
    private Integer customerId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("dob")
    private Date dob;
    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("avatarPath")
    private String avatarPath;
    @SerializedName("active")
    private boolean active;

    public Integer getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public boolean isActive() {
        return active;
    }
}