package com.ven.balu.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class SimpleCustomer {
    @SerializedName("customerId")
    private int customerId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("dob")
    private Date dob;
    @SerializedName("gender")
    private String gender;
    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("avatarPath")
    private String avatarPath;
    @SerializedName("active")
    private boolean active;

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDob() {
        return dob;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getAvatarPath() {
        return avatarPath;
    }
}
