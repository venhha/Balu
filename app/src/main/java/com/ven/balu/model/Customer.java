package com.ven.balu.model;

import java.sql.Date;

public class Customer {
    private Integer customerId;

    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;

    private String address;
    private String email;
    private String phoneNumber;

    private String username;
    private String password;
    private String avatarPath;
    private boolean isActive = true;

    // Getter Setter
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
        return isActive;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Private constructor --> cannot new Customer() outside this class
    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.dob = builder.dob;
        this.address = builder.address;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.username = builder.username;
        this.password = builder.password;
        this.avatarPath = builder.avatarPath;
        this.isActive = builder.isActive;
    }

    // static builder method || create Customer with required attributes
    public static Builder builder(Integer customerId, String firstName, String lastName, String username, String password) {
        return new Builder(customerId, firstName, lastName, username, password);
    }

    public static class Builder {
        private final Integer customerId;
        private final String firstName;
        private final String lastName;
        private final String username;
        private final String password;


        private String gender;
        private Date dob;
        private String address;
        private String email;
        private String phoneNumber;
        private String avatarPath;
        private boolean isActive = true;

        public Builder(Integer customerId, String firstName, String lastName, String username, String password) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
        }

        public Builder gender(String address) {
            this.gender = gender;
            return this;
        }

        public Builder dob(Date dob) {
            this.dob = dob;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder avatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}