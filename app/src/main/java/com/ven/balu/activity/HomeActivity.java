package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ven.balu.R;
import com.ven.balu.data.SharedPreferenceManager;

public class HomeActivity extends AppCompatActivity {
    LinearLayout btn_Home, btn_Profile, btn_Cart, btn_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title not the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_home);


        //
        bindViews();
    }

    private void handleCustomerLogOut() {
        SharedPreferenceManager manager = SharedPreferenceManager.getInstance(getApplicationContext());
        manager.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
    }



    private void bindViews() {
        btn_Home = findViewById(R.id.btn_Home);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_Cart = findViewById(R.id.btn_Cart);
        btn_Logout = findViewById(R.id.btn_logOut);

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCustomerLogOut();
            }
        });

        btn_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}