package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ven.balu.R;
import com.ven.balu.data.SharedPreferenceManager;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title not the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_intro);
        // Đặt delay trong 2 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Tạo Intent để chuyển sang Activity mới
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                // Kết thúc Activity hiện tại
                finish();
            }
        }, 2000); // 2000 milliseconds = 2 giây
    }
}