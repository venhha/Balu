package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ven.balu.R;
import com.ven.balu.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView tvName;
    private Button btnTest;
    private Button btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Liên kết biến TextView với id của TextView trong layout
        tvName = findViewById(R.id.tvName);
        btnTest = findViewById(R.id.btnTest);
        btnTest2 = findViewById(R.id.btnTest2);
        tvName.setText(Constants.ROOT_URL);

        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click btnTest2");
                getGG();
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click btnTest");
                getName();
            }
        });

        // Tạo yêu cầu Volley để lấy dữ liệu JSON

    }

    private void getGG() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvName.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvName.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void getName() {
        String url = Constants.ROOT_URL + "users/1";
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("create Queue");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Phân tích phản hồi JSON và lấy giá trị firstName
                    String firstName = response.getString("role");
                    System.out.println("get Name SUCCESS, name: " + firstName);
                    // Cập nhật TextView với giá trị firstName
                    tvName.setText(firstName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvName.setText("That didn't work!");
                error.printStackTrace();
            }
        });

        // Thêm yêu cầu vào hàng đợi Volley
        queue.add(request);
    }
}