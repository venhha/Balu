package com.ven.balu.dto;

import com.google.gson.annotations.SerializedName;
import com.ven.balu.model.Customer2;
import com.ven.balu.model.SimpleCustomer;

import java.io.Serializable;

public class LoginResponse2 {
    @SerializedName("isValid")
    private boolean isValid;
    @SerializedName("message")
    private String message;
    @SerializedName("customer")
    private SimpleCustomer customer;

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public SimpleCustomer getCustomer() {
        return customer;
    }
}
