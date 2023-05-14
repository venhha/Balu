package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    EditText et_username, et_password;
    TextView btn_signupHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // *init
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        // *processing
        try {
            checkUserInformationSaved();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bindViews();
    }
    private void handleLogin() {

        // Đối tượng giữ dữ liệu nhập vào --LoginRequest
        LoginRequest loginRequestData = new LoginRequest(et_username.getText().toString(), et_password.getText().toString());

        // Dùng retrofit để lấy dữ liệu từ server
        apiService = RetrofitClient.getInstance().getRetrofit(Constants.ROOT_URL).create(APIService.class);
        Call<LoginResponse> call = apiService.login(loginRequestData);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse res = response.body();
                if (res != null && res.isValid()) {
                    // Thông tin đăng nhập hợp lệ, Lưu thông tin đăng nhập
                    SharedPreferenceManager manager = SharedPreferenceManager.getInstance(getApplicationContext());
                    manager.saveCustomerId(res.getCustomer().getCustomerId()); //customerId
                    manager.saveCustomer(res.getCustomer()); //customer

                    // Chuyển đổi Activity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công với ID " + res.getCustomer().getCustomerId().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    // Thông tin đăng nhập không hợp lệ
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "System ERROR || onFailure !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkUserInformationSaved() {
        SharedPreferenceManager manager = SharedPreferenceManager.getInstance(this);
        if (manager.isLogin()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Đăng nhập với ID " + manager.getCustomerId(), Toast.LENGTH_SHORT).show();
        }
    }

    private void bindViews() {
        btnLogin = findViewById(R.id.btn_Login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_signupHere = findViewById(R.id.btn_signupHere);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    handleLogin();
                } catch (Exception e) {
                    System.out.println("ERROR handleLogin");
                    e.printStackTrace();
                }
            }
        });
        btn_signupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}