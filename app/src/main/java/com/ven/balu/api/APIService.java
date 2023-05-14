package com.ven.balu.api;

import com.ven.balu.dto.LoginRequest;
import com.ven.balu.dto.LoginResponse;
import com.ven.balu.dto.LoginResponse2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest requestData);

    @POST("auth/login")
    Call<LoginResponse2> login2(@Body LoginRequest requestData);
}
