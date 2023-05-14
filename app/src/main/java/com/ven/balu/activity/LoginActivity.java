package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.ven.balu.dto.LoginResponse2;

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
        // *init
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title not the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_login);
        // *processing
        try {
            checkUserInformationSaved();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bindViews();
    }

    private void testLogin() {
        // chứa dữ liệu được nhập từ user
        LoginRequest loginRequestData = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());
        System.out.println(edtUsername.getText().toString());
        System.out.println(edtPassword.getText().toString());
        // tạo đối tượng retrofit để xử lý dữ liệu từ server
        apiService = RetrofitClient.getInstance().getRetrofit(Constants.ROOT_URL).create(APIService.class);
        System.out.println("DONE apiService");
        Call<LoginResponse2> call = apiService.login2(loginRequestData);
        System.out.println("DONE init call");
        call.enqueue(new Callback<LoginResponse2>() {
            @Override
            public void onResponse(Call<LoginResponse2> call, Response<LoginResponse2> response) {
                LoginResponse2 res = response.body();
                System.out.println("DONE get res");

                System.out.println(res.getMessage());
                System.out.println(res.isValid());
                if (res.getCustomer().getCustomerId() != 0) {
                    System.out.println(res.getCustomer().getCustomerId());
                    System.out.println(res.getCustomer().getDob());
                    System.out.println(res.getCustomer().getUsername());
                    System.out.println(res.getCustomer().getPassword());
                    System.out.println(res.getCustomer().isActive());
                    System.out.println("DONE");
                } else {
                    System.out.println("cus null");
                    System.out.println(res.getCustomer().getCustomerId());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse2> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }

    private void handleLogin() {

        // Đối tượng giữ dữ liệu nhập vào --LoginRequest
        LoginRequest loginRequestData = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());

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
                    System.out.println(manager.isLogin());

                    // Chuyển đổi Activity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                } else {
                    // Thông tin đăng nhập không hợp lệ
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, tài khoản không tồn tại !", Toast.LENGTH_SHORT).show();
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
        edtUsername = findViewById(R.id.et_username);
        edtPassword = findViewById(R.id.et_password);

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
    }

}