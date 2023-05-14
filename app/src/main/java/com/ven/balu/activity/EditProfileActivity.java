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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ven.balu.R;
import com.ven.balu.api.APIService;
import com.ven.balu.api.RetrofitClient;
import com.ven.balu.api.VolleySingle;
import com.ven.balu.common.Constants;
import com.ven.balu.data.SharedPreferenceManager;
import com.ven.balu.dto.CustomerResponse;
import com.ven.balu.dto.EditProfileRequest;
import com.ven.balu.model.Customer;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    //    ImageView btn_changeAvatar;
    LinearLayout btn_SaveEditProfile;
    CircleImageView iv_avatar_profile;
    EditText et_FullName, et_Gender, et_Dob, et_Address, et_Email, et_PhoneNumber;
    TextView tvUsername, tvCustomerID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_profile);
        // *processing
        bindViews();
        try {
            loadData();
        } catch (Exception e) {
            System.out.println("ERROR EditProfileActivity");
            e.printStackTrace();
        }
    }

    private void handleSaveEditProfile() {
        try {
            SharedPreferenceManager manager = SharedPreferenceManager.getInstance(this);
            Customer customer = manager.getCustomer();

            customer.setFirstName(et_FullName.getText().toString());
            customer.setPhoneNumber(et_PhoneNumber.getText().toString());
            customer.setEmail(et_Email.getText().toString());
            customer.setAddress(et_Address.getText().toString());
            customer.setDob(et_Dob.getText().toString());
            customer.setGender(et_Gender.getText().toString());

            EditProfileRequest editProfileRequestData = new EditProfileRequest(
                    customer.getCustomerId(),
                    customer.getFirstName(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getDob(),
                    customer.getGender()
            );

            APIService service = RetrofitClient.getInstance().getRetrofit(Constants.ROOT_URL).create(APIService.class);
            Call<CustomerResponse> call = service.editProfile(editProfileRequestData);
            call.enqueue(new Callback<CustomerResponse>() {
                @Override
                public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                    CustomerResponse res = response.body();
                    if (res.getCustomerId() != 0) {
                        manager.saveCustomer(customer);
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lỗi !!!\n(Dữ liệu không hợp lệ)", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CustomerResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
                }
            });


//            String url = "http://localhost:8080/balu/api/customers/1";
        } catch (Exception e) {
            System.out.println("ERROR handleSaveEditProfile");
            e.printStackTrace();
        }

    }

    private void loadData() {
        SharedPreferenceManager manager = SharedPreferenceManager.getInstance(this);
        Customer customer = manager.getCustomer();

        et_FullName.setText(customer.getFirstName());
        tvUsername.setText(customer.getUsername()); //!
        tvCustomerID.setText(customer.getCustomerId().toString()); //!
        et_PhoneNumber.setText(customer.getPhoneNumber());
        et_Email.setText(customer.getEmail());
        et_Address.setText(customer.getAddress());
        et_Dob.setText(customer.getDob());
        et_Gender.setText(customer.getGender());
    }

    private void bindViews() {
//        btn_changeAvatar = findViewById(R.id.btn_changeAvatar);
        et_FullName = findViewById(R.id.et_FullName_profile);
        tvUsername = findViewById(R.id.tvUsername_profile);
        tvCustomerID = findViewById(R.id.tvCustomerID_profile);
        et_Gender = findViewById(R.id.et_Gender_profile);
        et_Dob = findViewById(R.id.et_Dob_profile);
        et_Address = findViewById(R.id.et_Address_profile);
        et_Email = findViewById(R.id.et_Email_profile);
        et_PhoneNumber = findViewById(R.id.et_PhoneNumber_profile);
        iv_avatar_profile = findViewById(R.id.iv_avatar_profile);
        btn_SaveEditProfile = findViewById(R.id.btn_SaveEditProfile);

        btn_SaveEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSaveEditProfile();
            }
        });
    }
}