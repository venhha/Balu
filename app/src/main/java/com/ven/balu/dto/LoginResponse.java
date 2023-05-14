package com.ven.balu.dto;

import com.google.gson.annotations.SerializedName;
import com.ven.balu.model.Customer;
import com.ven.balu.model.SimpleCustomer;

public class LoginResponse {
    @SerializedName("isValid")
    private boolean isValid;
    @SerializedName("message")
    private String message;
    @SerializedName("customer")
    private Customer customer;

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public Customer getCustomer() {
        return customer;
    }
}
