package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ven.balu.R;
import com.ven.balu.api.APIService;
import com.ven.balu.api.RetrofitClient;
import com.ven.balu.common.Constants;
import com.ven.balu.data.SharedPreferenceManager;
import com.ven.balu.dto.LoginRequest;
import com.ven.balu.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private APIService apiService;
    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });


    }

    private void handleLogin() {
        try {
            // Đối tượng giữ dữ liệu nhập vào --LoginRequest
            LoginRequest loginRequestData = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());
            // Dùng retrofit để lấy dữ liệu từ server
            apiService = RetrofitClient.getInstance().getRetrofit(Constants.ROOT_URL).create(APIService.class);
            Call<LoginResponse> call = apiService.login(loginRequestData);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse res = response.body();
                    if (res.isSuccess()) {
                        // Thông tin đăng nhập hợp lệ, Lưu thông tin đăng nhập
                        Long userID = res.getUser().getId();
                        SharedPreferenceManager.getInstance(getApplicationContext()).saveLoggedUserID(userID);

                        // Chuyển đổi Activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Thông tin đăng nhập không hợp lệ
                        System.out.println(res.getMessage());
                        Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không chính xác !!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "onFailure !!!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Lỗi code: handleLogin!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindViews() {
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
    }

}