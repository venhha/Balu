package com.ven.balu.api;

import com.ven.balu.dto.LoginRequest;
import com.ven.balu.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest requestData);
}
