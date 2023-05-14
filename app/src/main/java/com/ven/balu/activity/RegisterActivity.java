package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import com.ven.balu.dto.RegisterRequest;
import com.ven.balu.dto.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    TextView btn_loginHere;
    Button btn_signup;
    EditText et_username, et_password, et_password_repeat, et_phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        // *processing
        bindViews();
    }

    private void handleRegister() {
        if (et_password.getText().toString().equals(et_password_repeat.getText().toString())) {
            System.out.println(et_password.getText().toString());
            System.out.println(et_password_repeat.getText().toString());
            RegisterRequest requestData = new RegisterRequest(
                    et_username.getText().toString(),
                    et_password.getText().toString(),
                    et_phonenumber.getText().toString());

            APIService service = RetrofitClient.getInstance().getRetrofit(Constants.ROOT_URL).create(APIService.class);
            Call<RegisterResponse> call = service.register(requestData);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse res = response.body();
                    if (res.getCustomerId() != 0) {
                        finish();
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công\nMời bạn đăng nhập!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại (đã có username hoặc sđt)", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "onFailure RegisterActivity", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(RegisterActivity.this, "Lặp lại mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindViews() {
        btn_loginHere = findViewById(R.id.btn_loginHere);
        et_phonenumber = findViewById(R.id.et_phonenumber_signup);
        et_username = findViewById(R.id.et_username_signup);
        et_password = findViewById(R.id.et_password_signup);
        et_password_repeat = findViewById(R.id.et_passwordRepeat_signup);
        btn_loginHere = findViewById(R.id.btn_loginHere);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("btn_signup");
                    System.out.println(et_password.getText().toString());
                    System.out.println(et_password_repeat.getText().toString());
                    handleRegister();
                    System.out.println("DONE btn_loginHere");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERR btn_loginHere");
                }
            }
        });
        btn_loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_loginHere");
                finish();
            }
        });
    }
}