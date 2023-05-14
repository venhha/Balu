package com.ven.balu.model;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("customerId")
    private Integer customerId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("dob")
    private String dob;
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

    public String getDob() {
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

    public void setDob(String dob) {
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
        active = active;
    }

    public Customer() {
    }

    public Customer(Integer customerId, String firstName, String lastName, String gender, String dob, String address, String email, String phoneNumber, String username, String password, String avatarPath, boolean active) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.avatarPath = avatarPath;
        this.active = active;
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
        this.active = builder.active;
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
        private String dob;
        private String address;
        private String email;
        private String phoneNumber;
        private String avatarPath;
        private boolean active;

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

        public Builder dob(String dob) {
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

        public Builder isActive(boolean active) {
            this.active = active;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", active=" + active +
                '}';
    }
}